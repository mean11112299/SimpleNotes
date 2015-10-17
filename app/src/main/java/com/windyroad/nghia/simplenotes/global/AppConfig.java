package com.windyroad.nghia.simplenotes.global;

/**
 * Created by Nghia-PC on 10/3/2015.
 * Giá trị cố định sử dụng trong App
 */
public class AppConfig {


    //region App Path
    /** Thư mục chứa dữ liệu ứng dụng */
    public static final String PATH_DIRECTORY_APP = ".simplenote";

    /** Thư mục chứa cài đặt ứng dụng */
    public static final String PATH_DIRECTORY_SETTINGS = PATH_DIRECTORY_APP + "/shared_prefs";
    /** File cài đặt người dùng */
    public static final String PATH_USER_SETTINGS = PATH_DIRECTORY_SETTINGS + "user_settings";
    /** File cài đặt ứng dụng */
    public static final String PATH_APP_SETTINGS = PATH_DIRECTORY_SETTINGS + "app_settings";

    /** Thư mục chứa cài đặt ứng dụng */
    public static final String PATH_DIRECTORY_NOTEBOOKS = PATH_DIRECTORY_APP + "/notebooks";

    /** Tên thư mục đính kèm file */
    private static final String DIRECTORY_NAME_ATTACH = "files";
    /** Tên file data */
    private static final String FILE_NAME_DATA = "data";
    //endregion

    /**
     * TODO Set Group name toàn ứng dụng
     * @param groupName
     * @return
     */
    public static void setCurrentGroupName(String groupName){
        // mặc định là thư mục đầu tiên
    }

    /**
     * TODO Lấy Group Name toàn ứng dụng
     * @return
     */
    public static String getCurrentGroupName(){
        return "general";
    }

    /**
     * Lấy thư mục chứa thông tin GroupName
     * ứng dụng/ group name
     * @return
     */
    public static String getPathCurrentNotebook(){
        return PATH_DIRECTORY_NOTEBOOKS + "/" + getCurrentGroupName();
    }

    /**
     * Lấy File data phụ thuộc GroupName hiện tại
     * ứng dụng/ group name/ tên dữ liệu
     */
    public static String getPathCurrentData(){
        return getPathCurrentNotebook() + "/" + FILE_NAME_DATA;
    }

    /**
     * Lấy File đính kèm phụ thuộc GroupName hiện tại
     * ứng dụng/ group name/ files
     */
    public static String getPathFolderCurrentAttach(){
        return getPathCurrentNotebook() + "/" + DIRECTORY_NAME_ATTACH;
    }
}
