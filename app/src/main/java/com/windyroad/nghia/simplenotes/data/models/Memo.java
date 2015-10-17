package com.windyroad.nghia.simplenotes.data.models;

import java.util.Calendar;

/**
 * Created by Nghia on 11-10-15.
 */
public class Memo extends BaseDataObject {
    private String tags;
    private String title;
    private String dataText;
    private String dataCheckList;
    private String timeUser;
    private String location;
    private String weather;

    public Memo() {
    }

    public Memo(long _id, String uid, Calendar timeCreated, Calendar timeLastUpdated, String tags, String title, String dataText, String dataCheckList, String timeUser, String location, String weather) {
        super(_id, uid, timeCreated, timeLastUpdated);
        this.tags = tags;
        this.title = title;
        this.dataText = dataText;
        this.dataCheckList = dataCheckList;
        this.timeUser = timeUser;
        this.location = location;
        this.weather = weather;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataText() {
        return dataText;
    }

    public void setDataText(String dataText) {
        this.dataText = dataText;
    }

    public String getDataCheckList() {
        return dataCheckList;
    }

    public void setDataCheckList(String dataCheckList) {
        this.dataCheckList = dataCheckList;
    }

    public String getTimeUser() {
        return timeUser;
    }

    public void setTimeUser(String timeUser) {
        this.timeUser = timeUser;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
