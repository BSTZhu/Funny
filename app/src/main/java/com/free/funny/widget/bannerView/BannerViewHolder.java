package com.free.funny.widget.bannerView;

import android.content.Context;
import android.view.View;

/**
 * Created by zhujunlin on 2017/7/6.
 */

public interface BannerViewHolder<T> {

    /**
     * 创建View
     *
     * @param context
     * @return
     */
    View createView(Context context);

    /**
     * 绑定数据
     *
     * @param context
     * @param position
     * @param data
     */
    void onBind(Context context, int position, T data);
}
