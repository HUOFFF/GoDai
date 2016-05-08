package com.team7.godai.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.team7.godai.R;
import com.team7.godai.Service.UserService;
import com.team7.godai.adapter.FragmentAdapter;
import com.team7.godai.fragment.*;

import java.util.ArrayList;
import java.util.List;

public class PubActivity extends AppCompatActivity {

    //将ToolBar与TabLayout结合放入AppBarLayout
    private Toolbar mToolbar;
    //DrawerLayout中的左侧菜单控件
    private NavigationView mNavigationView;
    //DrawerLayout控件
    private DrawerLayout mDrawerLayout;
    //Tab菜单，主界面上面的tab切换菜单
    public TabLayout mTabLayout;
    //v4中的ViewPager控件
    public ViewPager mViewPager;

    TextView header;

    public static PubActivity pubActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pub);
        initView();
        pubActivity = this;
    }

    public void initView() {
        //MainActivity的布局文件中的主要控件初始化
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        header = (TextView) findViewById(R.id.header_name);

        //初始化ToolBar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        header.setText("ID: " + LoginActivity.getUsername() + "");

        //设置抽屉DrawerLayout
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //设置导航栏NavigationView的点击事件
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                UserService userService = new UserService(pubActivity);
                switch (menuItem.getItemId()) {
                    case R.id.item_one:
                        Intent intent = new Intent();
                        intent.setClass(PubActivity.this, User_edit_P.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.item_two:
                        Intent intent2 = new Intent();
                        intent2.setClass(PubActivity.this, Location_edit_P.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.item_three:
                        if (userService.is_express(LoginActivity.getUsername())) {
                            Log.i("TAG", "已成为快递员");
                            Toast.makeText(pubActivity, "已成为快递员", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent3 = new Intent();
                            intent3.setClass(PubActivity.this, Courier_edit_P.class);
                            finish();
                            startActivity(intent3);
                        }
                        break;
                    case R.id.item_four:
                        if (userService.is_express(LoginActivity.getUsername())) {
                            Intent intent4 = new Intent();
                            intent4.setClass(PubActivity.this, ExpActivity.class);
                            startActivity(intent4);
                            finish();
                        } else {
                            Log.i("TAG", "尚未成为快递员");
                            Toast.makeText(pubActivity, "尚未成为快递员", Toast.LENGTH_SHORT).show();
                        }
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
        titles.add("发布任务");
        titles.add("已发布任务");
        //初始化TabLayout的title
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new PubAssignmentFragment());
        fragments.add(new Pub_assignmentFragment());
        fragments.add(new Pubed_assigmentFragment());
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
            intent.setClass(PubActivity.this, LoginActivity.class);
            startActivity(intent);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
