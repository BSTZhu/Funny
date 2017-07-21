package com.free.funny.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.free.funny.base.flux.AppStore;
import com.free.funny.relax.base.flux.BaseStore;
import com.free.funny.relax.logic.base.RLBaseFragment;


/**
 * Created by zhujunlin on 17/7/4.
 */
public abstract class BaseFragment extends RLBaseFragment {

    protected AppStore mAppStore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppStore = AppStore.getInstance();
        if (getObject() != null) {
            BaseApplication.sDispatcher.register(getObject());
        }
        if (getStore() != null) {
            getStore().register();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getObject() != null) {
            BaseApplication.sDispatcher.unregister(getObject());
        }
        if (getStore() != null) {
            getStore().unregister();
        }
    }

    protected BaseStore getStore() {
        return null;
    }

    /**
     * 用于事件总线的自动注册
     *
     * @return 当前的Activity
     */
    protected abstract <T> T getObject();
}
