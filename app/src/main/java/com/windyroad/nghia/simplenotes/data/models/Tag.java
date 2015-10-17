package com.windyroad.nghia.simplenotes.data.models;

import java.util.Calendar;

/**
 * Created by Imark-N on 10/13/2015.
 */
public class Tag extends BaseDataObject{
    private String dataText;
    private String color;

    public Tag() {
    }

    public Tag(long _id, String uid, Calendar timeCreated, Calendar timeLastUpdated, String dataText, String color) {
        super(_id, uid, timeCreated, timeLastUpdated);
        this.dataText = dataText;
        this.color = color;
    }
}
