package com.windyroad.nghia.simplenotes.data;

import android.content.ContentValues;
import android.content.Context;

import com.windyroad.nghia.simplenotes.data.models.Memo;

/**
 * Created by Nghia-PC on 10/3/2015.
 * Lớp chỉnh sửa Dữ liệu memo
 */
public class MemoData extends BaseDataHandle {
    public MemoData(Context context) {
        super(context);
    }

    public static long add(Context context, Memo memo) {
        BaseDataHandle dbHandle = new BaseDataHandle(context);

        ContentValues values = new ContentValues();
        values.put(KEY_MEMO_TITLE, memo.getTitle());
        values.put(KEY_MEMO_DATA_TEXT, memo.getDataText());
        values.put(KEY_MEMO_TAGS, memo.getTags());
        values.put(KEY_MEMO_LOCATION, memo.getLocation());
        values.put(KEY_MEMO_TIME_USER, memo.getTimeUser());
        values.put(KEY_MEMO_WEATHER, memo.getWeather());

        return dbHandle.db.insert(TABLE_MEMO, null, values);
    }
}
