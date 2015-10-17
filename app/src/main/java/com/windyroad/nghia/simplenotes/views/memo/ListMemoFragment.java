package com.windyroad.nghia.simplenotes.views.memo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.windyroad.nghia.simplenotes.R;

/**
 * Danh sách ghi chú
 */
public class ListMemoFragment extends Fragment implements IListMemoView {

    /**
     * Khởi tạo mới Fragment
     */
    public static ListMemoFragment newInstance() {
        ListMemoFragment fragment = new ListMemoFragment();
        Bundle args = new Bundle();
        // note: Tạo tham số
        fragment.setArguments(args);
        return fragment;
    }

    public ListMemoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);  // có menu

        if (getArguments() != null) {
            // note: lấy tham số trong newInstance
            //mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_memo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews();
        initVars();
        setEvents();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list_memo, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_memo:
                startActivityAddMemo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void findViews() {

    }

    private void initVars() {

    }

    private void setEvents() {

    }

    private void startActivityAddMemo() {
        Intent intent = new Intent(getActivity(), AddMemoActivity.class);
        startActivity(intent);
    }
}
