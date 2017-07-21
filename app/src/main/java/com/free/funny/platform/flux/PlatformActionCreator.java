package com.free.funny.platform.flux;

import com.free.funny.base.BaseActivity;
import com.free.funny.base.BaseApplication;
import com.free.funny.base.BaseCallBack;
import com.free.funny.platform.api.LoginApi;
import com.free.funny.platform.flux.actions.LoginSuccessAction;
import com.free.funny.platform.model.LoginBean;
import com.free.funny.platform.model.LoginModel;
import com.free.funny.relax.logic.manager.RequestManager;
import com.free.funny.utils.MD5;

/**
 * Created by zhujunlin on 2017/7/4.
 */

public class PlatformActionCreator {

    private static PlatformActionCreator sInstance;
    private final LoginApi mLoginApi;

    public static PlatformActionCreator getInstance() {
        if (sInstance == null) {
            sInstance = new PlatformActionCreator();
        }
        return sInstance;
    }

    private PlatformActionCreator() {
        RequestManager rm = BaseApplication.sRequestManager;
        mLoginApi = rm.getService(LoginApi.class);
    }

    public void login(BaseActivity act, String userName, String password) {
        LoginBean loginBean = new LoginBean();
        loginBean.account = userName;
        loginBean.password = MD5.getMd5Value(password);
        loginBean.platform = "MOBILE";
        act.addRequest(mLoginApi.login(loginBean), new BaseCallBack<LoginModel>() {
            @Override
            public void onSuccess200(LoginModel loginModel) {
                BaseApplication.sDispatcher.dispatch(new LoginSuccessAction(loginModel));
            }
        });
    }
}
