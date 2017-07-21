package com.free.funny.relax.base.flux;

/**
 * Created by zhujunlin on 2017/5/26.
 */

public interface BaseStoreCallback<T> {

    void getNew();

    void useCache(T cache);

}
