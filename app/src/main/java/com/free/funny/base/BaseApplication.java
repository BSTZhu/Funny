package com.free.funny.base;

import android.content.Context;
import android.util.Log;

import com.free.funny.base.common.Constants;
import com.free.funny.base.flux.AppStore;
import com.free.funny.relax.base.flux.Dispatcher;
import com.free.funny.relax.logic.base.RLBaseApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by zhujunlin on 2017/7/3.
 */

public class BaseApplication extends RLBaseApplication {

    public static Dispatcher sDispatcher;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initConfig();
        sDispatcher = Dispatcher.getInstance();
        AppStore.getInstance().register();
    }

    private void initConfig() {
        sConfigManager.mBaseUrl = Constants.getStartPoint();
        refreshConfig();
    }

    @Override
    protected OkHttpClient getHttpClient() {
        return new OkHttpClient().newBuilder().connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build();
    }
}
