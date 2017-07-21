package com.free.funny.platform.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.free.funny.R;
import com.free.funny.widget.bannerView.BannerViewHolder;

/**
 * Created by zhujunlin on 2017/7/6.
 */

public class BannerViewHolderAdapter implements BannerViewHolder<Integer> {
    private ImageView iv_banner;

    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.item_widget_bannerview_banner, null);
        iv_banner = (ImageView) view.findViewById(R.id.iv_banner);
        return view;
    }

    @Override
    public void onBind(Context context, int position, Integer data) {
        // 数据绑定
        iv_banner.setImageResource(data);
    }
}
