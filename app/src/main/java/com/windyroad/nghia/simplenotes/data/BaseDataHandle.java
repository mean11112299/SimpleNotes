package com.windyroad.nghia.simplenotes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nghia-PC on 10/2/2015.
 * Hỗ trợ tạo dữ liệu, lớp cha của các lớp data
 */
public class BaseDataHandle extends SQLiteOpenHelper {

    private static final String LOG = BaseDataHandle.class.getSimpleName();

    public final SQLiteDatabase db;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "simple_note";


    //--- TABLE NAME ---
    public static final String TABLE_MEMO = "memo";
    public static final String TABLE_TAG = "tag";
    public static final String TABLE_ATTACH = "attach";


    //--- TABLE COLUMN, CREATE STATEMENT ---

    //region Column chung, tạo trong BaseDataObjects
    public static final String KEY_ID = "_id";  // sử dụng để liên kết phía client, kết nối nhanh hơn
    public static final String KEY_UID = "uid";  // sử dụng để đồng bộ với server, đưa ra cho ngừoi dùng xem
    public static final String KEY_TIME_CREATED = "time_created";  // thời gian tạo
    public static final String KEY_TIME_LAST_UPDATED = "time_last_updated";  // thời gian cập nhật cuối cùng
    public static final String CREATE_COMMON_COLUMNS =
            KEY_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    KEY_UID + " TEXT  NOT NULL  UNIQUE  DEFAULT (CURRENT_TIMESTAMP) , " +
                    KEY_TIME_CREATED + " DATETIME DEFAULT (CURRENT_TIMESTAMP) , " +
                    KEY_TIME_LAST_UPDATED + " DATETIME DEFAULT (CURRENT_TIMESTAMP) ";
    //endregion


    //region Table Memo
    public static final String KEY_MEMO_TAGS = "tags";  // tag trong memo, cách = kí tự ', '
    public static final String KEY_MEMO_TITLE = "title";  // tiêu đề
    public static final String KEY_MEMO_DATA_TEXT = "data_text";  // dữ liệu văn bản
    public static final String KEY_MEMO_DATA_CHECKLIST = "data_checklist";  // nếu là checklist => có dữ liệu, lưu trữ = json
    public static final String KEY_MEMO_TIME_USER = "time_user";  // thời gian người dùng
    public static final String KEY_MEMO_LOCATION = "location";  // vị trí (template: latitude, longitude)
    public static final String KEY_MEMO_PLACE = "place";  // địa điểm
    public static final String KEY_MEMO_WEATHER = "weather";  // thời tiết
    public static final String KEY_MEMO_EMOTION = "emotion";  // cảm xúc
    public static final String KEY_MEMO_PEOPLE = "peoples";  // người tham gia, lấy trong contact (template: peple1, peple2)
    public static final String KEY_MEMO_COLOR = "color";  // màu sắt dạng mã hex (#eee)
    public static final String KEY_MEMO_IS_STAR = "is_star";  // có gắn sao
    public static final String KEY_MEMO_IS_ARCHIVE = "is_archive";  // thành tựu =)) chắc không dùng
    public static final String KEY_MEMO_IS_DELETED = "is_deleted";  // Đánh đấu đã xóa, di chuyển vào thùng rác
    public static final String MEMO_CREATE_STATEMENT = "CREATE TABLE " +
            TABLE_MEMO + " ( " +

            CREATE_COMMON_COLUMNS + " ," +

            KEY_MEMO_TAGS + " TEXT, " +
            KEY_MEMO_TITLE + " TEXT, " +
            KEY_MEMO_DATA_TEXT + " TEXT, " +
            KEY_MEMO_DATA_CHECKLIST + " TEXT, " +
            KEY_MEMO_TIME_USER + " DATETIME DEFAULT (CURRENT_TIMESTAMP), " +
            KEY_MEMO_LOCATION + " TEXT, " +
            KEY_MEMO_PLACE + " TEXT, " +
            KEY_MEMO_WEATHER + " TEXT, " +
            KEY_MEMO_EMOTION + " TEXT, " +
            KEY_MEMO_PEOPLE + " TEXT, " +
            KEY_MEMO_COLOR + " TEXT, " +
            KEY_MEMO_IS_STAR + " BOOL, " +
            KEY_MEMO_IS_ARCHIVE + " BOOL, " +
            KEY_MEMO_IS_DELETED + " BOOL " +
            ")";
    //endregion


    //region Table Tag
    public static final String KEY_TAG_DATA_TEXT = "data_text";
    public static final String KEY_TAG_COLOR = "color";
    public static final String TAG_CREATE_STATEMENT = "CREATE TABLE " +
            TABLE_TAG + " ( " +

            CREATE_COMMON_COLUMNS + " ," +

            KEY_TAG_DATA_TEXT + " TEXT, " +
            KEY_TAG_COLOR + " TEXT " +
            ")";
    //endregion


    public static final String KEY_ATTACH_MEMO_ID = "memo_id";
    public static final String KEY_ATTACH_LAST_URI = "last_uri";  // đường dẫn file cuối (từ tên Notebook đến tên file)
    public static final String KEY_ATTACH_MIME = "mime";  // loại file (image/jpeg)
    //DELETE CASCADE: Xóa bảng cha, xóa luôn con
    public static final String ATTACH_CREATE_STATEMENT = "CREATE TABLE " +
            TABLE_ATTACH + " ( " +

            CREATE_COMMON_COLUMNS + ", " +

            KEY_ATTACH_MEMO_ID + " INTEGER, " +
            KEY_ATTACH_LAST_URI + " TEXT, " +
            KEY_ATTACH_MIME + " TEXT, " +

            "CONSTRAINT "+ TABLE_ATTACH+"_fk FOREIGN KEY " +
            "("+KEY_ATTACH_MEMO_ID+") REFERENCES "+TABLE_MEMO+"("+KEY_ID+") ON DELETE CASCADE " +
            ")";

    public BaseDataHandle(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MEMO_CREATE_STATEMENT);
        db.execSQL(TAG_CREATE_STATEMENT);
        db.execSQL(ATTACH_CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTACH);

        // Tạo lại
        onCreate(db);
    }

    /**
     * Đóng Database
     */
    public void closeDB() {
        if (this.db != null)
            if (this.db.isOpen())
                this.db.close();
    }
}
