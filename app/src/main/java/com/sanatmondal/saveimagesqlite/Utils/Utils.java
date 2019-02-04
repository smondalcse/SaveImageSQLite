package com.sanatmondal.saveimagesqlite.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Utils {
    public static byte[] getByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 98, stream);
        return stream.toByteArray();
    }

    public static Bitmap getImage(byte[] data){
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }
}
