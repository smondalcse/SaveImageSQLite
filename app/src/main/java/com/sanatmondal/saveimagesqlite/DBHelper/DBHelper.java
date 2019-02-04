package com.sanatmondal.saveimagesqlite.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.v4.widget.SwipeRefreshLayout;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelper extends SQLiteAssetHelper {

    private static final String DB_Name = "SaveBitmap.db";
    private static final int DB_VER = 1;
    private static final String TABLE_NAME = "Gallary";
    private static final String COL_Name = "Name";
    private static final String COL_Data = "Data";

    public DBHelper(Context context) {
        super(context, DB_Name, null, DB_VER);
    }

    public void addBitmap(String name, byte[] image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_Name, name);
        cv.put(COL_Data, image);
        database.insert(TABLE_NAME, null, cv);

    }

    public byte[] getBitmapByName(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] select = {COL_Name, COL_Data};
        qb.setTables(TABLE_NAME);
        Cursor c = qb.query(database, select, "Name = ?", new String[]{name}, null, null, null);
        byte[] result = null;
        if(c.moveToFirst()){
            do {
                result = c.getBlob(c.getColumnIndex(COL_Data));
            } while (c.moveToNext());
        }

        return result;
    }


}
