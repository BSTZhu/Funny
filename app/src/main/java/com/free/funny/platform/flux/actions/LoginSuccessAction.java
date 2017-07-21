package com.free.funny.platform.flux.actions;

import com.free.funny.platform.model.LoginModel;
import com.free.funny.relax.base.flux.BaseAction;

/**
 * Created by zhujunlin on 2017/7/12.
 */

public class LoginSuccessAction extends BaseAction {
    private LoginModel mData;

    public LoginSuccessAction(LoginModel model) {
        mData = model;
    }

    @Override
    public LoginModel getData() {
        return mData;
    }

    public boolean isSuccess() {
        return mData != null;
    }
}
