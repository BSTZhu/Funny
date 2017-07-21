package com.free.funny.platform.ctrlview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.free.funny.R;
import com.free.funny.base.BaseActivity;
import com.free.funny.base.BaseFragment;
import com.free.funny.base.flux.AppStore;
import com.free.funny.platform.adapter.BannerViewHolderAdapter;
import com.free.funny.platform.flux.PlatformActionCreator;
import com.free.funny.relax.logic.utils.PreferencesUtils;
import com.free.funny.relax.logic.utils.ToastUtils;
import com.free.funny.widget.bannerView.BannerHolderCreator;
import com.free.funny.widget.bannerView.BannerPageClickListener;
import com.free.funny.widget.bannerView.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujunlin on 2017/7/5.
 * 首页Fragment
 */

public class HomeFragment extends BaseFragment {

    public static final int[] RES = new int[]{R.drawable.image01, R.drawable.image02, R.drawable.image03};
    private List<Integer> mImageList = new ArrayList<>();
    private BannerView bv_banner;
    private ImageView civ_customer_icon;

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.platform_fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        civ_customer_icon = (ImageView) view.findViewById(R.id.civ_customer_icon);
        bv_banner = (BannerView) view.findViewById(R.id.bv_banner);
    }

    private void initData() {
        civ_customer_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity parent = (HomeActivity) getActivity();
                parent.openMenu();
            }
        });

        bv_banner.setBannerPageClickListener(new BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(getContext(), "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });

        for (int RE : RES) {
            mImageList.add(RE);
        }

        bv_banner.setIndicatorVisible(true);
        bv_banner.setPages(mImageList, new BannerHolderCreator<BannerViewHolderAdapter>() {
            @Override
            public BannerViewHolderAdapter createViewHolder() {
                return new BannerViewHolderAdapter();
            }
        });
    }

    @Override
    protected HomeFragment getObject() {
        return this;
    }
}
