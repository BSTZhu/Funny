package com.free.funny.relax.base.flux;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujunlin on 2017/5/26.
 */

public abstract class BaseStore {

    Dispatcher mDispatcher;
    private final HashMap<String, Long> mLastStoreTimeMap;
    private int mRegisterCount;

    public BaseStore() {
        mDispatcher = Dispatcher.getInstance();
        mLastStoreTimeMap = new HashMap<>();
    }

    public void register() {
        mRegisterCount++;
        mDispatcher.register(this);
    }

    public void unregister() {
        mRegisterCount--;
        if (mRegisterCount == 0) {
            mDispatcher.unregister(this);
        }
    }

    public void post(BaseEvent event) {
        mDispatcher.post(event);
    }

    public boolean needRefresh(String key) {
        long time = System.currentTimeMillis();
        return time - getLastStoreTime(key) > getCacheTime(key);
    }

    protected void refreshStoreTime(String key) {
        mLastStoreTimeMap.put(key, System.currentTimeMillis());
    }

    public long getLastStoreTime(String key) {
        if (mLastStoreTimeMap.containsKey(key)) {
            return mLastStoreTimeMap.get(key);
        }
        return 0;
    }

    public long getCacheTime(String key) {
        Map<String, Long> timeMap = getCacheTimeMap();
        if (timeMap == null || timeMap.size() == 0 || !timeMap.containsKey(key)) {
            return 0;
        }
        return timeMap.get(key);
    }

    /**
     * 获取当前Store存储的状态数据，如果需要刷新，则回调callback
     *
     * @return
     */
    public void getData(String key, BaseStoreCallback callback) {
        if (needRefresh(key)) {
            callback.getNew();
        } else if (getCache(key) != null) {
            callback.useCache(key);
        }
    }

    protected abstract Map<String, Long> getCacheTimeMap();

    public abstract <T> T getCache(String key);
}
