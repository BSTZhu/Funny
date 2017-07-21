package com.free.funny.widget.bannerView;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujunlin on 2017/7/6.
 */

public class BannerPagerAdapter<T> extends PagerAdapter {

    private List<T> mDatas;
    private BannerHolderCreator mBannerHolderCreator;
    private boolean canLoop;
    private BannerPageClickListener mPageClickListener;
    private final int mLooperCountFactor = 500;
    private ViewPager mViewPager;

    public BannerPagerAdapter(List<T> datas, BannerHolderCreator holderCreator, boolean canLoop, ViewPager viewPager) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }

        for (T t : datas) {
            mDatas.add(t);
        }

        mBannerHolderCreator = holderCreator;
        this.canLoop = canLoop;
        mViewPager = viewPager;
    }

    public void setPageClickListener(BannerPageClickListener pageClickListener) {
        mPageClickListener = pageClickListener;
    }

    /**
     * 初始化Adapter和设置当前选中的Item
     */
    public void setUpViewViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.setAdapter(this);
        mViewPager.getAdapter().notifyDataSetChanged();
        int currentItem = canLoop ? getStartSelectItem() : 0;
        //设置当前选中的Item
        mViewPager.setCurrentItem(currentItem);
    }

    private int getStartSelectItem() {
        // 我们设置当前选中的位置为Integer.MAX_VALUE / 2,这样开始就能往左滑动
        // 但是要保证这个值与getRealPosition 的 余数为0，因为要从第一页开始显示
        int currentItem = getRealCount() * mLooperCountFactor / 2;
        if (currentItem % getRealCount() == 0) {
            return currentItem;
        }
        // 直到找到从0开始的位置
        while (currentItem % getRealCount() != 0) {
            currentItem++;
        }
        return currentItem;
    }

    /**
     * 获取真实的Count
     */
    private int getRealCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setDatas(List<T> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return canLoop ? getRealCount() * mLooperCountFactor : getRealCount();//ViewPager返回int 最大值;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = getView(position, container);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        // 轮播模式才执行
        if (canLoop) {
            int position = mViewPager.getCurrentItem();
            if (position == getCount() - 1) {
                position = 0;
                mViewPager.setCurrentItem(position, false);
            }
        }
    }

    private View getView(int position, ViewGroup container) {
        final int realPosition = position % getRealCount();
        BannerViewHolder holder = null;
        holder = mBannerHolderCreator.createViewHolder();

        if (holder == null) {
            throw new RuntimeException("can not return a null holder");
        }

        View view = holder.createView(container.getContext());

        if (mDatas != null && mDatas.size() > 0) {
            holder.onBind(container.getContext(), realPosition, mDatas.get(realPosition));
        }

        // 添加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPageClickListener != null) {
                    mPageClickListener.onPageClick(v, realPosition);
                }
            }
        });
        return view;
    }
}
