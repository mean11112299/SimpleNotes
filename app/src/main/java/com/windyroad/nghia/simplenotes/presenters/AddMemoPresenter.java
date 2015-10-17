package com.windyroad.nghia.simplenotes.presenters;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.windyroad.nghia.common.CalendarUtil;
import com.windyroad.nghia.common.ConvertUtil;
import com.windyroad.nghia.common.fragment.DatePickerFragment;
import com.windyroad.nghia.common.fragment.TimePickerFragment;
import com.windyroad.nghia.common.network.ConnectionType;
import com.windyroad.nghia.common.network.NetworkUtil;
import com.windyroad.nghia.common.service.LocationService;
import com.windyroad.nghia.simplenotes.R;
import com.windyroad.nghia.simplenotes.data.MemoData;
import com.windyroad.nghia.simplenotes.data.models.Memo;
import com.windyroad.nghia.simplenotes.data.models.Tag;
import com.windyroad.nghia.simplenotes.global.AppSettings;
import com.windyroad.nghia.simplenotes.network.webservices.models.GetLocationNameResponse;
import com.windyroad.nghia.simplenotes.views.memo.IAddMemoView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Imark-N on 10/10/2015.
 */
public class AddMemoPresenter implements IAddMemoPresenter {

    private IAddMemoView mView;
    private Context mContext;

    private ArrayList<Tag> mListTag;
    private LocationService mLocationService;
    private Calendar mCalUser;

    public AddMemoPresenter(Context context, IAddMemoView view) {

        this.mContext = context;
        this.mView = view;

        initAddNewData();
    }

    /**
     * Khởi tạo giá trị khi thêm mới
     */
    private void initAddNewData() {
        initUserDateTime();
        initLocationName();
        // get weather
    }

    private void initUserDateTime() {
        mCalUser = mCalUser == null ? Calendar.getInstance() : mCalUser;
        mView.setDatetime(mCalUser);
    }

    private void initLocationName() {
        updateLocationNameHandle(false);
    }


    @Override
    public void updateTagsHandle() {
        mView.openChosenTags(mListTag);
    }


    @Override
    public void updateLocationNameHandle(boolean checkValidate) {

        mView.displayWaiting(true);

        // lấy vị trí lại
        mLocationService = new LocationService(mContext);

        // validate
        if (checkValidate) {
            if (!validateLocation()) {
                return;
            }
        }

        updateLocationName();
    }

    private boolean validateLocation() {
        // kiểm tra mạng
        if (NetworkUtil.getConnectivityStatus(mContext) == ConnectionType.NOT_CONNECTED) {
            mView.requestOpenNetwork();
            return false;
        }

        // kiểm tra GPS
        if (!mLocationService.canGetLocation()) {
            mView.requestOpenGPS();
            return false;
        }

        return true;
    }

    private void updateLocationName() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = String.format(
                "https://maps.googleapis.com/maps/api/geocode/json?latlng=%1s&key=%2s",
                mLocationService.getLatitude() + "," + mLocationService.getLongitude(),
                mContext.getString(R.string.google_maps_key));

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        GetLocationNameResponse object = new Gson().fromJson(response, GetLocationNameResponse.class);
                        if (object.getStatus().equalsIgnoreCase(GetLocationNameResponse.STATUS_OK)) {
                            String locationName = object.getResults().get(0).getFormatted_address();
                            mView.setLocation(locationName);
                        } else {
                            mView.setErrorGetLocationName(object.getStatus());
                        }

                        mView.displayWaiting(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mView.setErrorGetLocationName(error.toString());

                mView.displayWaiting(false);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    @Override
    public void updateDateTimeHandle()
    {
        mView.openTimePicker(mCalUser, new TimePickerFragment.IListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                // cập nhật lại thời gian, hiển thị lại
                mCalUser = CalendarUtil.setValue(mCalUser, hourOfDay, minute);
                mView.setDatetime(mCalUser);
            }
        });

        mView.openDatePicker(mCalUser, new DatePickerFragment.IListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // cập nhật lại thời gian, hiển thị lại
                mCalUser = CalendarUtil.setValue(mCalUser, year, monthOfYear, dayOfMonth);
                mView.setDatetime(mCalUser);
            }
        });
    }


    @Override
    public void saveMemo(String title, String dataText, String tags, String timeUser, String location, String weather) {
        Memo memo = new Memo();

        memo.setTitle(title);
        memo.setDataText(dataText);
        memo.setTags(tags);
        memo.setTimeUser(timeUser);
        memo.setLocation(location);
        memo.setWeather(weather);

        long id = MemoData.add(mContext, memo);

        mView.returnResultOk(id);
    }
}
