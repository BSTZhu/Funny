package com.free.funny.relax.base.network;

/**
 * Created by zhujunlin on 2017/5/26.
 */

public interface RequestStateListener<T> {

    void onStart();

    void onFinish();

    void onSuccess(T body);

    void onFailure();
}
