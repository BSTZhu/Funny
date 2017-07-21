package com.free.funny.platform.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.free.funny.R;

/**
 * Created by zhujunlin on 2017/7/10.
 */

public class HomeMenuListAdapter extends BaseAdapter {

    private int[] mMenuIcons;
    private String[] mMenuItems;
    private Context mContext;
    private final LayoutInflater mInflater;

    public HomeMenuListAdapter(Context context, int[] menuIcons, String[] menuItems) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mMenuIcons = menuIcons;
        mMenuItems = menuItems;
    }

    @Override
    public int getCount() {
        return mMenuIcons.length;
    }

    @Override
    public Object getItem(int position) {
        return mMenuItems[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_platform_home_menu, null);
            holder = new ViewHolder();
            holder.iv_menu_icon = (ImageView) convertView.findViewById(R.id.iv_menu_icon);
            holder.tv_menu = (TextView) convertView.findViewById(R.id.tv_menu);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.iv_menu_icon.setImageDrawable(mContext.getDrawable(mMenuIcons[position]));
        holder.tv_menu.setText(mMenuItems[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView iv_menu_icon;
        TextView tv_menu;
    }
}
