package com.windyroad.nghia.simplenotes.presenters;

import java.util.Calendar;

/**
 * Created by Imark-N on 10/13/2015.
 */
public interface IAddMemoPresenter {


    /**
     * Cập nhật lại Tags
     */
    void updateTagsHandle();

    /**
     * Lấy vị trí
     * @param validate
     */
    void updateLocationNameHandle(boolean validate);

    /**
     * Cập nhật lại thời gian
     */
    void updateDateTimeHandle();

    /**
     * Lưu thông tin
     * @param title
     * @param text
     * @param tags
     * @param timeUser
     * @param location
     * @param weather
     */
    void saveMemo(String title, String text, String tags, String timeUser, String location, String weather);

}
