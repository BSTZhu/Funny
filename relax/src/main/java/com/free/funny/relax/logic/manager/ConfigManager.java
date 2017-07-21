package com.free.funny.relax.logic.manager;

/**
 * Created by zhujunlin on 17/5/27.
 */

public class ConfigManager {

    private static ConfigManager sInstance;

    public static ConfigManager getInstance() {
        if (sInstance == null) {
            sInstance = new ConfigManager();
        }
        return sInstance;
    }

    private ConfigManager() {
    }

    // 全局Application的DEBUG开关，LogUtils有依赖
    public boolean mDebugOn = true;

    // retrofit请求的baseUrl
    public String mBaseUrl = "https://api.github.com";
}
