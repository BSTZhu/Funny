package com.free.funny.relax.logic.manager;

import android.os.Handler;
import android.text.TextUtils;

import com.free.funny.relax.base.network.ErrorHandlingCallAdapter;
import com.free.funny.relax.base.network.ErrorHandlingCallBack;
import com.free.funny.relax.base.network.RequestStateListener;
import com.free.funny.relax.base.network.SimpleCall;
import com.free.funny.relax.base.network.SimpleCallBack;
import com.free.funny.relax.base.network.converters.RLConverterFactory;
import com.free.funny.relax.base.network.cookie.PersistentCookieJar;
import com.free.funny.relax.base.network.progress.ProgressListener;
import com.free.funny.relax.base.network.progress.ProgressResponseBody;
import com.free.funny.relax.logic.base.RLBaseApplication;
import com.free.funny.relax.logic.utils.PackageUtils;
import com.free.funny.relax.logic.utils.PreferencesUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by zhujunlin on 17/5/27.
 */

public class RequestManager {

    private static RequestManager sInstance;

    public static RequestManager getInstance(OkHttpClient client) {
        if (sInstance == null) {
            sInstance = new RequestManager(client);
        }
        return sInstance;
    }

    private RequestManager(OkHttpClient client) {
        refreshConfig();
        HttpLoggingInterceptor.Level l = HttpLoggingInterceptor.Level.NONE;
        if (PackageUtils.isApkDebugable(RLBaseApplication.sAppContext)) {
            l = HttpLoggingInterceptor.Level.BODY;
        }
        mCookieJar = new PersistentCookieJar();
        mClient = client.newBuilder()
                .addInterceptor(mTokenInterceptor)
                .cookieJar(mCookieJar)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(l))
                .build();
        mHandler = new Handler();
        getRetrofit(mDefaultBaseUrl);
    }

    private Handler mHandler;

    private OkHttpClient mClient;
    private String mDefaultBaseUrl;
    private Map<String, Retrofit> mRetrofitMap = new HashMap<>();
    private PersistentCookieJar mCookieJar;

    public void refreshConfig() {
        ConfigManager cm = ConfigManager.getInstance();
        mDefaultBaseUrl = cm.mBaseUrl;
    }

    private Retrofit getRetrofit(String endPoint) {
        if (mRetrofitMap.containsKey(endPoint)) {
            return mRetrofitMap.get(endPoint);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(mClient)
                .addCallAdapterFactory(new ErrorHandlingCallAdapter.ErrorHandlingCallAdapterFactory())
                .addConverterFactory(RLConverterFactory.create())
                .build();
        mRetrofitMap.put(endPoint, retrofit);
        return retrofit;
    }

    public PersistentCookieJar getCookieJar() {
        return mCookieJar;
    }

    public <R> R getService(Class<R> c) {
        return getService(mDefaultBaseUrl, c);
    }

    public <R> R getService(String endPoint, Class<R> c) {
        return getRetrofit(endPoint).create(c);
    }

    public <R> void addRequest(final SimpleCall call, final SimpleCallBack callBack,
                               final RequestStateListener listener) {
        if (listener != null) {
            listener.onStart();
        }
        call.enqueue(new ErrorHandlingCallBack<R>() {
            @Override
            public void success(final Response<R> response) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        PreferencesUtils.putString(RLBaseApplication.sAppContext, "token", response.headers().get("Authorization"));
                        if (callBack != null) {
                            callBack.onSuccess(response.body());
                        }
                        if (listener != null) {
                            listener.onSuccess(response.body());
                            listener.onFinish();
                        }
                    }
                });
            }

            @Override
            public void unauthenticated(final Response<?> response) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onFailure(response.body());
                        }
                        if (listener != null) {
                            listener.onFailure();
                            listener.onFinish();
                        }
                    }
                });
            }

            @Override
            public void clientError(final Response<?> response) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onFailure(response.body());
                        }
                        if (listener != null) {
                            listener.onFailure();
                            listener.onFinish();
                        }
                    }
                });
            }

            @Override
            public void serverError(final Response<?> response) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onFailure(response.body());
                        }
                        if (listener != null) {
                            listener.onFailure();
                            listener.onFinish();
                        }
                    }
                });
            }

            @Override
            public void networkError(final IOException e) {
                e.printStackTrace();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onError(e);
                        }
                        if (listener != null) {
                            listener.onFailure();
                            listener.onFinish();
                        }
                    }
                });
            }

            @Override
            public void unexpectedError(final Throwable t) {
                t.printStackTrace();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            callBack.onError(new Exception(t));
                        }
                        if (listener != null) {
                            listener.onFailure();
                            listener.onFinish();
                        }
                    }
                });
            }
        });
    }

    public Call download(String url, final ProgressListener listener) {
        OkHttpClient client = mClient.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                final okhttp3.Response originalResponse = chain.proceed(chain.request());
                //包装响应体并返回
                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), listener))
                        .build();
            }
        }).build();
        final Request request = new Request.Builder()
                .addHeader("Accept-Encoding", "identity")
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (listener == null) {
                    return;
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final okhttp3.Response response) throws IOException {
                if (listener == null) {
                    return;
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        int code = response.code();
                        if (code >= 200 && code < 300) {
                            listener.onSuccess(response.body().byteStream());
                        } else if (code == 401) {
                            listener.onFailure(response);
                        } else if (code >= 400 && code < 500) {
                            listener.onFailure(response);
                        } else if (code >= 500 && code < 600) {
                            listener.onFailure(response);
                        } else {
                            listener.onError(new RuntimeException("Unexpected response " + response));
                        }
                    }
                });
            }
        });
        return call;
    }

    public Handler getHandler() {
        return mHandler;
    }

    Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request authorised;
            if (!TextUtils.isEmpty(PreferencesUtils.getString(RLBaseApplication.sAppContext, "token"))) {
                authorised = originalRequest.newBuilder()
                        .header("Authorization", PreferencesUtils.getString(RLBaseApplication.sAppContext, "token"))
                        .build();
            } else {
                authorised = originalRequest.newBuilder().build();
            }
            return chain.proceed(authorised);
        }
    };
}
