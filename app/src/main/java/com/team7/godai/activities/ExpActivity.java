package com.team7.godai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.team7.godai.R;
import com.team7.godai.adapter.FragmentAdapter;
import com.team7.godai.fragment.ExpAssignmentFragment;
import com.team7.godai.fragment.Re_AssignmentFragment;

import java.util.ArrayList;
import java.util.List;

public class ExpActivity extends AppCompatActivity {

    //将ToolBar与TabLayout结合放入AppBarLayout
    private Toolbar mToolbar;
    //DrawerLayout中的左侧菜单控件
    private NavigationView mNavigationView;
    //DrawerLayout控件
    private DrawerLayout mDrawerLayout;
    //Tab菜单，主界面上面的tab切换菜单
    private TabLayout mTabLayout;
    //v4中的ViewPager控件
    public ViewPager mViewPager;
    //

    public static ExpActivity expActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exp);
        initView();
        expActivity = this;
    }

    public void initView() {
        //MainActivity的布局文件中的主要控件初始化
        mToolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        mTabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) this.findViewById(R.id.view_pager);



        //初始化ToolBar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);

        //设置抽屉DrawerLayout
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //设置导航栏NavigationView的点击事件
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_one:
                        Intent intent = new Intent();
                        intent.setClass(ExpActivity.this,User_edit.class);
                        startActivity(intent);
                        break;
                    case R.id.item_two:
                        Intent intent1 = new Intent();
                        intent1.setClass(ExpActivity.this,PubActivity.class);
                        startActivity(intent1);
                        break;
                }
                menuItem.setChecked(true);//点击了把它设为选中状态
                mDrawerLayout.closeDrawers();//关闭抽屉
                return true;
            }
        });



        //初始化TabLayout的title数据集
        List<String> titles = new ArrayList<>();
        titles.add("悬赏栏");
        titles.add("已领取任务");
        //初始化TabLayout的title
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ExpAssignmentFragment());
        fragments.add(new Re_AssignmentFragment());
        //创建ViewPager的adapter
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.setClass(ExpActivity.this, LoginActivity.class);
            startActivity(intent);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
