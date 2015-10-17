package com.windyroad.nghia.simplenotes.views.memo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.windyroad.nghia.common.ConvertUtil;
import com.windyroad.nghia.common.activity.ActivityUtil;
import com.windyroad.nghia.common.fragment.DatePickerFragment;
import com.windyroad.nghia.common.fragment.TimePickerFragment;
import com.windyroad.nghia.simplenotes.R;
import com.windyroad.nghia.simplenotes.data.models.Tag;
import com.windyroad.nghia.simplenotes.global.AppAlertDialog;
import com.windyroad.nghia.simplenotes.global.AppSettings;
import com.windyroad.nghia.simplenotes.presenters.AddMemoPresenter;
import com.windyroad.nghia.simplenotes.presenters.IAddMemoPresenter;

import java.util.ArrayList;
import java.util.Calendar;

public class AddMemoActivity extends AppCompatActivity {

    public static final String EXTRA_MEMO_ID = "memo_id";
    IAddMemoPresenter mPresenter;
    ProgressDialog mProgressDialog;

    EditText mEditText_Title;
    EditText mEditText_Memo;
    TextView mTextView_Tags;
    TextView mTextView_DateTime;
    TextView mTextView_Location;
    TextView mTextView_Weather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        setupToolbar();
        findViews();
        initVars();
        setEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_memo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // TODO: 10/10/2015 save memo
                return true;
            case R.id.action_save:
                mPresenter.saveMemo(
                        mEditText_Title.getText().toString(),
                        mEditText_Memo.getText().toString(),
                        mTextView_Tags.getText().toString(),
                        mTextView_DateTime.getText().toString(),
                        mTextView_Location.getText().toString(),
                        mTextView_Weather.getText().toString()
                );
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Cài đặt Toolbar
     */
    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Main);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            /*actionBar.setDisplayHomeAsUpEnabled(true);  // hiển thị Home/ Up
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_48dp);  // đổi ảnh Home

            // Hiện icon
            actionBar.setHomeButtonEnabled(true);
            actionBar.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));*/
        }
    }

    private void findViews() {
        mEditText_Title = (EditText) findViewById(R.id.editText_Title);
        mEditText_Memo = (EditText) findViewById(R.id.editText_Memo);
        mTextView_Tags = (TextView) findViewById(R.id.editText_Tags);
        mTextView_DateTime = (TextView) findViewById(R.id.editText_DateTime);
        mTextView_Location = (TextView) findViewById(R.id.editText_Location);
        mTextView_Weather = (TextView) findViewById(R.id.editText_Weather);
    }

    private void initVars() {
        mProgressDialog = new ProgressDialog(this);
        mPresenter = new AddMemoPresenter(this, mAddMemoView);
    }

    private void setEvents() {
        mTextView_Tags.setOnClickListener(mTextView_Tags_Click);
        mTextView_Location.setOnClickListener(mTextView_Location_Click);
        mTextView_DateTime.setOnClickListener(mTextView_DateTime_Click);
    }


    View.OnClickListener mTextView_Location_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.updateLocationNameHandle(true);
        }
    };


    View.OnClickListener mTextView_DateTime_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.updateDateTimeHandle();
        }
    };

    View.OnClickListener mTextView_Tags_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.updateTagsHandle();
        }
    };


    IAddMemoView mAddMemoView = new IAddMemoView() {

        @Override
        public void setDatetime(Calendar calendar) {
            String datetime = ConvertUtil.Calendar2String(calendar,
                    AppSettings.getDateTimeFormat(getBaseContext()), null);
            mTextView_DateTime.setText(datetime);
        }

        @Override
        public void setLocation(String location) {
            mProgressDialog.dismiss();

            mTextView_Location.setText(location);
        }

        @Override
        public void setWeather(String weather) {
            mTextView_Weather.setText(weather);
        }

        @Override
        public void setErrorGetLocationName(String error) {
            mProgressDialog.dismiss();

            String msg = getString(R.string.message_error_plus) + error;
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
        }

        @Override
        public void requestOpenNetwork() {
            AppAlertDialog.showNetworkSettingAlert(AddMemoActivity.this);
        }

        @Override
        public void requestOpenGPS() {
            AppAlertDialog.showLocationSettingAlert(AddMemoActivity.this);
        }

        @Override
        public void openChosenTags(ArrayList<Tag> tags) {
            // // TODO: 10/13/2015 stop here, bắt đầu làm tag lại
        }

        @Override
        public void openDatePicker(Calendar calendar, DatePickerFragment.IListener dateListener) {
            DatePickerFragment datePicker = DatePickerFragment.newInstance(calendar);
            datePicker.setListener(dateListener);
            datePicker.show(getSupportFragmentManager(), "date_picker");
        }

        @Override
        public void openTimePicker(Calendar calendar, TimePickerFragment.IListener timeListener) {
            TimePickerFragment timePicker = TimePickerFragment.newInstance(calendar);
            timePicker.setListener(timeListener);
            timePicker.show(getSupportFragmentManager(), "time_picker");
        }

        @Override
        public void displayWaiting(boolean show) {
            if (show){
                AppAlertDialog.showWaitingDialog(getBaseContext(), mProgressDialog);
            } else {
                mProgressDialog.dismiss();
                mProgressDialog.cancel();
            }
        }

        @Override
        public void returnResultOk(long id) {

            Intent data = new Intent();
            data.putExtra(EXTRA_MEMO_ID, id);
            ActivityUtil.setResultOk(AddMemoActivity.this, data);

            finish();
        }

        @Override
        public void returnResultCancel() {
            finish();
        }

    };

}
