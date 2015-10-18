package com.windyroad.nghia.simplenotes.views.others;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.windyroad.nghia.common.SharedPreferencesUtil;
import com.windyroad.nghia.common.drawer.DrawerMenuAdapter;
import com.windyroad.nghia.common.drawer.DrawerMenuDivider;
import com.windyroad.nghia.common.drawer.DrawerMenuHeader;
import com.windyroad.nghia.common.drawer.DrawerMenuItem;
import com.windyroad.nghia.simplenotes.R;

import java.util.ArrayList;

public class DrawerMenuFragment extends Fragment {

    private static final String PREF_NAME_DRAWER_MENU_POSITION = "drawer_menu_position";
    private ListView mListView_MenuItem;
    private ArrayList<DrawerMenuItem> mListMenuItem;
    private DrawerMenuAdapter mAdapterDrawerMenu;
    private Listener mListener;

    DrawerLayout mDrawerLayout;  // layout chứa Drawer
    ActionBarDrawerToggle mDrawerLayout_onToggle;  // Drawer Layout chuyển đổi
    private View mView_Drawer;  // View Fragment hiện tại (để đóng mở Drawer Menu)

    public void setListener (Listener listener) {
        this.mListener = listener;
    }

    /**
     * Cài đặt Navigation Drawer Fragment
     * @return
     */
    public void setUp(DrawerLayout drawerLayout, int fragmentID) {
        mView_Drawer = getActivity().findViewById(fragmentID);

        mDrawerLayout = drawerLayout;

        mDrawerLayout_onToggle = new ActionBarDrawerToggle(
                getActivity(),
                mDrawerLayout,
                R.string.nav_drawer_open, R.string.nav_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // Mở Navigation Drawer, load lại position

                int position = getMenuSelectedPosition();
                // // TODO: 9/26/2015 Chọn menu lại không hoạt động
                mListView_MenuItem.setItemChecked(position, true);
                mListView_MenuItem.setSelection(position);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerLayout_onToggle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_drawer_menu, container, false);

        findViews(rootView);
        initVars();
        setEvents();

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    //region Setup, Load

    private void findViews(View rootView) {
        mListView_MenuItem = (ListView) rootView.findViewById(R.id.listView_MenuItem);
    }

    private void initVars() {
        mListMenuItem = new ArrayList<DrawerMenuItem>();

        mListMenuItem.add(new DrawerMenuItem(R.drawable.ic_book_48dp, getString(R.string.title_book), ""));
        mListMenuItem.add(new DrawerMenuItem(R.drawable.ic_memo_48dp, getString(R.string.title_memos), ""));
        mListMenuItem.add(new DrawerMenuItem(R.drawable.ic_tag_48dp, getString(R.string.title_tags), ""));
        mListMenuItem.add(new DrawerMenuDivider());  // line
        mListMenuItem.add(new DrawerMenuHeader(getString(R.string.action_filter)));  // header
        mListMenuItem.add(new DrawerMenuItem(R.drawable.ic_reminder_48dp, getString(R.string.title_reminders), ""));
        mListMenuItem.add(new DrawerMenuItem(R.drawable.ic_attach_48dp, getString(R.string.title_attack), ""));
        mListMenuItem.add(new DrawerMenuItem(R.drawable.ic_star_48dp, getString(R.string.title_stars), ""));
        mListMenuItem.add(new DrawerMenuDivider());  // line
        mListMenuItem.add(new DrawerMenuItem(R.drawable.ic_setting_48dp, getString(R.string.title_reminders), ""));
        mListMenuItem.add(new DrawerMenuItem(R.drawable.ic_feedback_48dp, getString(R.string.title_feedback), ""));
        mListMenuItem.add(new DrawerMenuItem(R.drawable.ic_info_48dp, getString(R.string.title_info), ""));

        mAdapterDrawerMenu = new DrawerMenuAdapter(getActivity(), mListMenuItem);
        mListView_MenuItem.setAdapter(mAdapterDrawerMenu);
    }

    private void setEvents() {
        mListView_MenuItem.setOnItemClickListener(mListView_MenuItem_onItemClick);
    }

    //endregion


    //region EVENT

    AdapterView.OnItemClickListener mListView_MenuItem_onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            DrawerMenuItem menuItem = mListMenuItem.get(position);
            mListener.menuItemClick(menuItem);


            mDrawerLayout.closeDrawer(mView_Drawer);  // click item => returnResultCancel drawer
            saveMenuSelectedPosition(position);
            getActivity().invalidateOptionsMenu();  // Khởi tạo lại Option Menu, ActionBar
        }
    };

    //endregion

    private void saveMenuSelectedPosition(int position){
        SharedPreferencesUtil.put(getActivity(), PREF_NAME_DRAWER_MENU_POSITION, position+"");
    }

    private int getMenuSelectedPosition(){
        String value = SharedPreferencesUtil.get(getActivity(), PREF_NAME_DRAWER_MENU_POSITION, "-1");
        return Integer.valueOf(value);
    }


    /**
     * Lớp tương tác
     */
    public interface Listener {
        void menuItemClick(DrawerMenuItem menuItem);
    }
}
