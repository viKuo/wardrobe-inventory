package com.vivienlk.wardrobeinventory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.vivienlk.wardrobeinventory.database.WardrobeDbSchema.WardrobeTable;

/**
 * Created by Vivien on 8/14/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "database.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + WardrobeTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
                WardrobeTable.Cols.UUID + ", " +
                WardrobeTable.Cols.ITEM + ", " +
                WardrobeTable.Cols.DATE + ", " +
                WardrobeTable.Cols.COLORS + ", " +
                WardrobeTable.Cols.TEXTURES + ", " +
                WardrobeTable.Cols.OCCASIONS + ", " +
                WardrobeTable.Cols.SEASONS + ", " +
                WardrobeTable.Cols.FIT + ", " +
                WardrobeTable.Cols.LENGTH + ", " +
                WardrobeTable.Cols.PRICE + ", " +
                WardrobeTable.Cols.BRAND + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
