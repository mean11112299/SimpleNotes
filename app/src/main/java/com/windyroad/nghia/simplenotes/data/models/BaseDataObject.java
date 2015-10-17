package com.windyroad.nghia.simplenotes.data.models;

import java.util.Calendar;

/**
 * Created by Imark-N on 10/13/2015.
 */
public class BaseDataObject {
    private long _id;
    private String uid;
    private Calendar timeCreated;
    private Calendar timeLastUpdated;

    public BaseDataObject() {
    }

    public BaseDataObject(long _id, String uid, Calendar timeCreated, Calendar timeLastUpdated) {
        this._id = _id;
        this.uid = uid;
        this.timeCreated = timeCreated;
        this.timeLastUpdated = timeLastUpdated;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Calendar getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Calendar timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Calendar getTimeLastUpdated() {
        return timeLastUpdated;
    }

    public void setTimeLastUpdated(Calendar timeLastUpdated) {
        this.timeLastUpdated = timeLastUpdated;
    }
}
