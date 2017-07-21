package com.free.funny.relax.base.network;

/**
 * Created by zhujunlin on 2017/5/26.
 */

public interface SimpleCall<T> {

    void cancel();

    void enqueue(ErrorHandlingCallBack<T> callBack);

    SimpleCall<T> clone();

}
