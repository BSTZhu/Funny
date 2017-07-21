package com.free.funny.platform.ctrlview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.customization.dragableview.DragableView;
import com.free.funny.R;
import com.free.funny.base.BaseActivity;
import com.free.funny.personInfo.ctrlview.PersonInfoFragment;
import com.free.funny.platform.adapter.HomeMenuListAdapter;
import com.free.funny.platform.model.LiveListModel;
import com.free.funny.relax.logic.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by zhujunlin on 2017/7/4.
 * 首页Activity
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private static final int INDEX_HOME = 0;
    private static final int INDEX_MYINFO = 1;
    private int mIndex;
    private ArrayList<LiveListModel.LiveInfo> mLiveList = new ArrayList<>();
    private HomeFragment mHomeFragment;
    private PersonInfoFragment mPersonInfoFragment;
    private DragableView dv_bottom_home;
    private DragableView dv_bottom_person;
    private FrameLayout fl_home_container;
    private FrameLayout fl_person_container;
    private CoordinatorLayout platform_activity_home;
    private DrawerLayout platform_activity_group;
    private boolean isDrawer = false;
    private RelativeLayout rl_home_menu;
    private ListView lv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.platform_activity_home);
        hideTitleBar();
        initView();
        initFragment();
        initData();
    }

    private void initView() {
        platform_activity_group = (DrawerLayout) findViewById(R.id.platform_activity_group);
        platform_activity_home = (CoordinatorLayout) findViewById(R.id.platform_activity_home);
        rl_home_menu = (RelativeLayout) findViewById(R.id.rl_home_menu);
        lv_menu = (ListView) findViewById(R.id.lv_menu);
        fl_home_container = (FrameLayout) findViewById(R.id.fl_home_container);
        fl_person_container = (FrameLayout) findViewById(R.id.fl_person_container);
        dv_bottom_home = (DragableView) findViewById(R.id.dv_bottom_home);
        dv_bottom_person = (DragableView) findViewById(R.id.dv_bottom_person);
        dv_bottom_home.setBigIcon(R.drawable.bubble_big);
        dv_bottom_home.setSmallIcon(R.drawable.bubble_small);
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mPersonInfoFragment = new PersonInfoFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_home_container, mHomeFragment)
                .add(R.id.fl_person_container, mPersonInfoFragment)
                .commit();
    }

    private void initData() {
        dv_bottom_home.setOnClickListener(this);
        dv_bottom_person.setOnClickListener(this);

        int[] menuIcons = new int[]{R.drawable.clock, R.drawable.clock, R.drawable.clock, R.drawable.clock};
        final String[] menuItems = new String[]{"我的超级会员", "QQ钱包", "个性装扮", "我的收藏"};
        HomeMenuListAdapter menuListAdapter = new HomeMenuListAdapter(this, menuIcons, menuItems);
        lv_menu.setAdapter(menuListAdapter);
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show(HomeActivity.this, menuItems[position]);
            }
        });

        //获取屏幕的宽高
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        final Display display = manager.getDefaultDisplay();
        platform_activity_group.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer = true;
                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                platform_activity_home.layout(rl_home_menu.getRight(), platform_activity_home.getTop(), rl_home_menu.getRight() + display.getWidth(), platform_activity_home.getBottom());
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawer = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        platform_activity_home.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isDrawer && rl_home_menu.dispatchTouchEvent(event);
            }
        });
    }

    private void resetIcon() {
        dv_bottom_home.setBigIcon(R.drawable.pre_bubble_big);
        dv_bottom_home.setSmallIcon(R.drawable.pre_bubble_small);
        dv_bottom_person.setBigIcon(R.drawable.pre_person_big);
        dv_bottom_person.setSmallIcon(R.drawable.pre_person_small);
    }

    @Override
    public void onClick(View v) {
        resetIcon();
        switch (v.getId()) {
            case R.id.dv_bottom_home:
                dv_bottom_home.setBigIcon(R.drawable.bubble_big);
                dv_bottom_home.setSmallIcon(R.drawable.bubble_small);
                showHomeFragment();
                break;
            case R.id.dv_bottom_person:
                dv_bottom_person.setBigIcon(R.drawable.person_big);
                dv_bottom_person.setSmallIcon(R.drawable.person_small);
                showPersonInfoFragment();
                break;
        }
    }

    private void showHomeFragment() {
        if (mIndex != INDEX_HOME) {
            mIndex = INDEX_HOME;
            fl_home_container.setVisibility(View.VISIBLE);
            fl_person_container.setVisibility(View.GONE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(mPersonInfoFragment)
                    .show(mHomeFragment)
                    .commitAllowingStateLoss();
        }
    }

    private void showPersonInfoFragment() {
        if (mIndex != INDEX_MYINFO) {
            mIndex = INDEX_MYINFO;
            fl_home_container.setVisibility(View.GONE);
            fl_person_container.setVisibility(View.VISIBLE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(mHomeFragment)
                    .show(mPersonInfoFragment)
                    .commitAllowingStateLoss();
        }
    }

    public void openMenu() {
        platform_activity_group.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (platform_activity_group.isDrawerOpen(GravityCompat.START)) {
            platform_activity_group.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected HomeActivity getObject() {
        return this;
    }
}
