package com.free.funny.relax.base.flux;

import java.util.Map;

/**
 * Created by zhujunlin on 2017/5/26.
 */

public class NoCacheStore extends BaseStore {
    @Override
    protected Map<String, Long> getCacheTimeMap() {
        return null;
    }

    @Override
    public <T> T getCache(String key) {
        return null;
    }
}
