package com.windyroad.nghia.simplenotes.views.tag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.windyroad.nghia.simplenotes.R;

/**
 * Created by Imark-N on 10/14/2015.
 */
public class ListTagFragment extends Fragment {

    public ListTagFragment() {
    }

    public static ListTagFragment newInstance() {

        Bundle args = new Bundle();

        ListTagFragment fragment = new ListTagFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);  // có menu
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_tag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_tag, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Cài đặt Toolbar
     */
    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        activity.setSupportActionBar(toolbar);

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {

            /*actionBar.setDisplayHomeAsUpEnabled(true);  // hiển thị Home/ Up
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_48dp);  // đổi ảnh Home

            // Hiện icon
            actionBar.setHomeButtonEnabled(true);
            actionBar.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));*/
        }
    }
}
