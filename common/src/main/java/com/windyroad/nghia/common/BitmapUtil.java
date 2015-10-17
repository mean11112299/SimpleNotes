package com.windyroad.nghia.common;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Nghia-PC on 9/8/2015.
 */
public class BitmapUtil {

    /** Đổi cỡ Bitmap
     * Chưa test*/
    public static Bitmap resize(Bitmap bitmapSource, int maxSize){

        int sourceWidth = bitmapSource.getWidth();
        int sourceHeight = bitmapSource.getHeight();
        int targetWidth = maxSize;
        int targetHeight = maxSize;

        //if Source Width > Height => Resize Height theo Width;
        if (sourceWidth > sourceHeight){
            targetHeight = (int)((float)targetWidth * sourceHeight / sourceWidth );
        } else {
            targetWidth = (int)((float)targetHeight * sourceWidth / sourceHeight);
        }

        return Bitmap.createScaledBitmap(bitmapSource, targetWidth, targetHeight, false);
    }

    /** Lưu ảnh Bitmap
     * Chưa test
     * @param bitmap
     * @param savePath
     * @param format
     * @param quality 0>100
     * @throws IOException
     * return true: thành công
     */
    public static boolean save(Bitmap bitmap, String savePath, Bitmap.CompressFormat format, int quality) {
        try {
            // save
            File outFile = new File(savePath);
            FileOutputStream fos = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Đổi kích thước và Lưu
     * @param bitmap
     * @param maxSize
     * @param savePath
     * @return TRue: thành công
     */
    public static boolean resizeAndSave(Bitmap bitmap, int maxSize, String savePath) {
        Bitmap resultBitmap = BitmapUtil.resize(bitmap, maxSize);
        return BitmapUtil.save(resultBitmap, savePath, Bitmap.CompressFormat.JPEG, 100);
    }
}
