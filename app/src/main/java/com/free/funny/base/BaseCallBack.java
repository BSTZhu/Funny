package com.free.funny.base;

import android.text.TextUtils;

import com.free.funny.R;
import com.free.funny.relax.base.network.SimpleCallBack;
import com.free.funny.relax.logic.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhujunlin on 17/7/4.
 */
public abstract class BaseCallBack<T> implements SimpleCallBack<T> {

    @Override
    public void onSuccess(T t) {
        if (t instanceof BaseModel) {
            BaseModel m = (BaseModel) t;
            if (m.success) {// 请求处理成功
                onSuccess200(t);
            } else {
                ToastUtils.show(BaseApplication.sAppContext, m.message);
            }
        } else if (t instanceof JSONObject) {
            JSONObject jo = (JSONObject) t;
            boolean status = false;
            String message = "";
            try {
                status = jo.getBoolean("success");
                message = jo.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (status) {
                onSuccess200(t);
            } else if (!TextUtils.isEmpty(message)) {
                ToastUtils.show(BaseApplication.sAppContext, message);
            }
        }
        onFinish(t);
    }

    @Override
    public void onFailure(Object o) {
    }

    @Override
    public void onError(Exception e) {
    }

    /**
     * override for custom case when http success
     *
     * @param t
     */
    public void onFinish(T t) {
    }

    public abstract void onSuccess200(T t);
}
