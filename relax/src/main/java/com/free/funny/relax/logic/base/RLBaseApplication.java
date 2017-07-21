package com.free.funny.relax.logic.base;

import android.app.Application;
import android.content.Context;

import com.free.funny.relax.logic.manager.ConfigManager;
import com.free.funny.relax.logic.manager.RequestManager;

import okhttp3.OkHttpClient;

/**
 * Created by zhujunlin on 2017/5/26.
 */

public abstract class RLBaseApplication extends Application {

    public static Context sAppContext;
    public static ConfigManager sConfigManager;
    public static RequestManager sRequestManager;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        sAppContext = this;
        sConfigManager = ConfigManager.getInstance();
        sRequestManager = RequestManager.getInstance(getHttpClient());
        refreshConfig();
    }

    public static void refreshConfig() {
        sRequestManager.refreshConfig();
    }

    protected abstract OkHttpClient getHttpClient();
}
