package com.windyroad.nghia.simplenotes.views.memo;

import com.windyroad.nghia.common.fragment.DatePickerFragment;
import com.windyroad.nghia.common.fragment.TimePickerFragment;
import com.windyroad.nghia.simplenotes.data.models.Tag;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Imark-N on 10/10/2015.
 */
public interface IAddMemoView {

    void setDatetime(Calendar calendar);

    void setLocation(String location);

    void setWeather(String weather);

    void setErrorGetLocationName(String error);

    /**
     * Yêu cầu mở mạng
     */
    void requestOpenNetwork();

    /**
     * Yêu cầu mở GPS
     */
    void requestOpenGPS();


    /**
     * Mở Chọn Tag
     * @param tags
     */
    void openChosenTags(ArrayList<Tag> tags);

    /**
     * mở chọn ngày
     * @param dateListener
     */
    void openDatePicker(Calendar calendar, DatePickerFragment.IListener dateListener);

    /**
     * Mở chọn giờ
     * @param calendar
     * @param timeListener
     */
    void openTimePicker(Calendar calendar, TimePickerFragment.IListener timeListener);

    /**
     * Ẩn / Hiện Chờ đợi
     * @param show
     */
    void displayWaiting(boolean show);

    /**
     * Trả về Activity Cha OK
     * @param id
     */
    void returnResultOk(long id);

    /**
     * Trả về Activity Hủy
     */
    void returnResultCancel();

}
