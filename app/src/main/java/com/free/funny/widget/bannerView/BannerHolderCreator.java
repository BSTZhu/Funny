package com.free.funny.widget.bannerView;

/**
 * Created by zhujunlin on 2017/7/6.
 */

public interface BannerHolderCreator<T extends BannerViewHolder> {
    /**
     * 创建ViewHolder
     */
    T createViewHolder();
}
