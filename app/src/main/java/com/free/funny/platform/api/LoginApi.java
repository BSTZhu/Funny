package com.free.funny.platform.api;

import com.free.funny.base.common.Constants;
import com.free.funny.platform.model.LoginBean;
import com.free.funny.platform.model.LoginModel;
import com.free.funny.relax.base.network.SimpleCall;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zhujunlin on 2017/7/12.
 */

public interface LoginApi {

    @POST(Constants.BASIC + "/login/login")
    SimpleCall<LoginModel> login(@Body LoginBean bean);
}
