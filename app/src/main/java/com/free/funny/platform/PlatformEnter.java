package com.free.funny.platform;

import android.content.Intent;

import com.free.funny.base.BaseActivity;
import com.free.funny.platform.ctrlview.HomeActivity;
import com.free.funny.platform.ctrlview.LoginActivity;

/**
 * Created by zhujunlin on 2017/7/4.
 */

public class PlatformEnter {

    public static void openLogin(BaseActivity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    public static void openHome(BaseActivity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }
}
