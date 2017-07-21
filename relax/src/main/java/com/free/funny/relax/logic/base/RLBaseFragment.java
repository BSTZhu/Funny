package com.free.funny.relax.logic.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhujunlin on 17/7/4.
 */
public abstract class RLBaseFragment extends Fragment {

    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getContentView(inflater, container, savedInstanceState);
    }

    public abstract View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

}
