package com.windyroad.nghia.simplenotes.views.others;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.windyroad.nghia.common.FragmentUtils;
import com.windyroad.nghia.common.activity.BaseActivity;
import com.windyroad.nghia.common.drawer.DrawerMenuItem;
import com.windyroad.nghia.simplenotes.R;
import com.windyroad.nghia.simplenotes.views.memo.ListMemoFragment;
import com.windyroad.nghia.simplenotes.views.tag.ListTagFragment;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        setupDrawerMenu();
        findViews();
        initVars();

        /*findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                // Hiện menu vị trí START
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //region Init
    private void findViews(){

    }

    private void initVars(){
        // mặc định ListMemo
        fragMenu_setSelect(new ListMemoFragment(), getString(R.string.title_memos));
    }

    /**
     * Cài đặt Toolbar
     */
    private void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Main);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);  // hiển thị Home/ Up
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_48dp);  // đổi ảnh Home

            // Hiện icon
            actionBar.setHomeButtonEnabled(true);
            actionBar.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        }
    }

    /** Cài đặt Drawer Menu */
    private void setupDrawerMenu() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // ---- Setup Navigation Drawer Fragment - chỉ Để nhận sự kiện ----
        mFragmentManager = getSupportFragmentManager();
        DrawerMenuFragment menuFrag = (DrawerMenuFragment) mFragmentManager.findFragmentById(R.id.frag_drawer_menu);

        menuFrag.setUp(mDrawerLayout, R.id.frag_drawer_menu);
        menuFrag.setListener(fragMenu_listener);
    }
    //endregion

    //region EVENT
    /**
     * Menu Fragment chon Item
     */
    DrawerMenuFragment.Listener fragMenu_listener = new DrawerMenuFragment.Listener() {
        @Override
        public void menuItemClick(DrawerMenuItem menuItem) {

            // Tìm fragment
            Fragment fragment = null;
            Class fragmentClass = ListMemoFragment.class;
            if (menuItem.getTitle().equals(getString(R.string.title_memos))){
                fragmentClass = ListMemoFragment.class;
            } else if (menuItem.getTitle().equals(getString(R.string.title_tags))) {
                fragmentClass = ListTagFragment.class;
            }

            // khởi tạo Fragment
            try{
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // hiển thị
            fragMenu_setSelect(fragment, menuItem.getTitle());
            //menuItem.setChecked(true);
        }
    };
    //endregion

    //region HELPER

    /**
     * Đổi Fragment khi menu đổi chọn Item
     * @param fragment
     */
    private void fragMenu_setSelect(Fragment fragment, String title) {
        FragmentUtils.replaceFragment(this, R.id.layout_MainContainer, fragment, null);
        setTitle(title);  // đổi Title
        invalidateOptionsMenu();  // update menu (thêm Item)

        mDrawerLayout.closeDrawers();
    }


    //endregion
}
