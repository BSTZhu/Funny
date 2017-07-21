package com.free.funny.platform.ctrlview;

import android.os.Bundle;

import com.free.funny.base.BaseActivity;
import com.free.funny.platform.PlatformEnter;

/**
 * Created by zhujunlin on 2017/7/4.
 */

public class AppEntranceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 做一些页面显示前的初始化工作  比如 检查是否升级版本

        PlatformEnter.openLogin(this);
        finish();
    }

    @Override
    protected AppEntranceActivity getObject() {
        return this;
    }
}
