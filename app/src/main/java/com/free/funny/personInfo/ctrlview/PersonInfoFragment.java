package com.free.funny.personInfo.ctrlview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.free.funny.R;
import com.free.funny.base.BaseActivity;
import com.free.funny.base.BaseFragment;
import com.free.funny.base.flux.AppStore;
import com.free.funny.platform.PlatformEnter;
import com.free.funny.relax.logic.utils.ToastUtils;

/**
 * Created by zhujunlin on 2017/7/5.
 */

public class PersonInfoFragment extends BaseFragment {

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personinfo_fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        if (!AppStore.getInstance().isLogin()) {
            PlatformEnter.openLogin((BaseActivity) getActivity());
        } else {
            ToastUtils.show(getActivity(), "lalalallala");
        }
    }

    @Override
    protected PersonInfoFragment getObject() {
        return this;
    }
}
