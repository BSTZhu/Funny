package com.free.funny.relax.base.network;

/**
 * Created by zhujunlin on 2017/5/26.
 */

public interface SimpleCallBack<T> {

    void onSuccess(T body);

    void onFailure(Object body);

    void onError(Exception e);
}
