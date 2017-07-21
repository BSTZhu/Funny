package com.free.funny.base.flux;

import com.free.funny.base.BaseApplication;
import com.free.funny.base.common.SPKeys;
import com.free.funny.platform.flux.actions.LoginSuccessAction;
import com.free.funny.platform.model.LoginModel;
import com.free.funny.relax.base.flux.BaseEvent;
import com.free.funny.relax.base.flux.NoCacheStore;
import com.free.funny.relax.logic.utils.PreferencesUtils;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by zhujunlin on 2017/7/3.
 */

public class AppStore extends NoCacheStore {
    private static AppStore sInstance;
    private LoginModel mLoginInfo;

    public static AppStore getInstance() {
        if (sInstance == null) {
            sInstance = new AppStore();
        }
        return sInstance;
    }

    private AppStore() {
        mLoginInfo = PreferencesUtils.getGson(BaseApplication.sAppContext, SPKeys.APP_LOGIN_INFO,
                LoginModel.class);
    }

    /**
     * 判断用户是否已经登录
     */
    public boolean isLogin() {
        return mLoginInfo != null;
    }

    /**
     * 获取用户Token
     */
    public String getToken() {
        return PreferencesUtils.getString(BaseApplication.sAppContext, "token");
    }

    /**
     * 保存用户登录信息，存在SP中，同时更新用户连续登录次数
     */
    private boolean saveLoginInfo(LoginModel model) {
        String lastLoginUser = PreferencesUtils.get(BaseApplication.sAppContext, SPKeys.APP_LAST_LOGIN_USER, "");
        boolean userChanged = !model.data.userId.equals(lastLoginUser);
        if (userChanged) {
            clearLoginInfo(false);
        }
        syncLoginInfo(model);
        PreferencesUtils.put(BaseApplication.sAppContext, SPKeys.APP_LAST_LOGIN_USER, model.data.userId);
        return userChanged;
    }

    /**
     * 清除用户登录信息，同时移除登录授权
     */
    private void clearLoginInfo(boolean clearCookie) {
        mLoginInfo = null;
        PreferencesUtils.put(BaseApplication.sAppContext, SPKeys.APP_LOGIN_INFO, "");
        if (clearCookie) {
            BaseApplication.sRequestManager.getCookieJar().getCookieStore().removeAll();
        }
    }

    private void syncLoginInfo(LoginModel model) {
        mLoginInfo = model;
        PreferencesUtils.putGson(BaseApplication.sAppContext, SPKeys.APP_LOGIN_INFO, model);// 保存登录信息
    }

    @Subscribe
    public void saveUserInfo(LoginSuccessAction action) {
        if (action.isSuccess()) {
            boolean userChanged = saveLoginInfo(action.getData());
            post(new LoginSuccessEvent(userChanged));
        }
    }

    public class LoginSuccessEvent implements BaseEvent {

        private boolean userChanged;

        public LoginSuccessEvent(boolean userChanged) {
            this.userChanged = userChanged;
        }

        public boolean isUserChanged() {
            return userChanged;
        }
    }
}
