package com.free.funny.platform.ctrlview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.free.funny.R;
import com.free.funny.base.BaseActivity;
import com.free.funny.base.flux.AppStore;
import com.free.funny.platform.PlatformEnter;
import com.free.funny.platform.flux.PlatformActionCreator;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by zhujunlin on 2017/7/12.
 * 登陆页面
 */

public class LoginActivity extends BaseActivity {

    private EditText et_login_user;
    private EditText et_login_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.platform_activity_login);
        setLeftVisibility(false);
        initView();
        initData();
    }

    private void initView() {
        et_login_user = (EditText) findViewById(R.id.et_login_user);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        setRightText(ICON_CLOSE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlatformActionCreator.getInstance().login(LoginActivity.this,
                        et_login_user.getText().toString(),
                        et_login_password.getText().toString());
            }
        });
    }

    @Subscribe
    public void success(AppStore.LoginSuccessEvent event) {
        PlatformEnter.openHome(this);
        finish();
    }

    @Override
    protected LoginActivity getObject() {
        return this;
    }
}
