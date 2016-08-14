package com.vivienlk.wardrobeinventory.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.vivienlk.wardrobeinventory.database.DatabaseHelper;

/**
 * Created by Vivien on 8/14/2016.
 */
public class WardrobeItem {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public WardrobeItem(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext)
                .getWritableDatabase();

    }
}
