package com.vivienlk.wardrobeinventory.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vivienlk.wardrobeinventory.database.DatabaseHelper;
import com.vivienlk.wardrobeinventory.database.WardrobeDbSchema;
import com.vivienlk.wardrobeinventory.database.WardrobeDbSchema.WardrobeTable.Cols;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Vivien on 8/14/2016.
 */
public class WardrobeItem {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private UUID mId;
    private String mItem;
    private String mDate;
    private String mColors;
    private String mTextures;
    private String mOccasions;
    private String mSeasons;
    private String mFit;
    private String mLength;
    private double mPrice;
    private String mBrand;

    public WardrobeItem(Context context, UUID id, String item,
                        String date, String colors, String textures,
                        String occasions, String seasons, String fit,
                        String length, double price, String brand) {
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext)
                .getWritableDatabase();
        mId = id;
        mItem = item;
        mDate = date;
        mColors = colors;
        mTextures = textures;
        mOccasions = occasions;
        mSeasons = seasons;
        mFit = fit;
        mLength = length;
        mPrice = price;
        mBrand = brand;
    }

    private ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID, mId.toString());
        values.put(Cols.ITEM, mItem);
        values.put(Cols.DATE, mDate);
        values.put(Cols.COLORS, mColors);
        values.put(Cols.TEXTURES, mTextures);
        values.put(Cols.OCCASIONS, mOccasions);
        values.put(Cols.SEASONS, mSeasons);
        values.put(Cols.FIT, mFit);
        values.put(Cols.LENGTH, mLength);
        values.put(Cols.PRICE, mPrice);
        values.put(Cols.BRAND, mBrand);
        return values;
    }

    public void save() {
        ContentValues values = getContentValues();
        mDatabase.insert(WardrobeDbSchema.WardrobeTable.NAME, null, values);
    }

    public void update() {
        ContentValues values = getContentValues();
        mDatabase.update(WardrobeDbSchema.WardrobeTable.NAME,
                values,
                Cols.UUID + " = ?",
                new String[] { mId.toString() });
    }

    private Cursor queryItem (String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                WardrobeDbSchema.WardrobeTable.NAME,
                null, // null selects all columns
                whereClause,
                whereArgs,
                null, //group by
                null, //having
                null //order by
        );
        return cursor;
    }


    public String getItem() {
        return mItem;
    }

    public void setItem(String item) {
        mItem = item;
    }

    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String brand) {
        mBrand = brand;
    }

    public Date getDate() throws ParseException {
        DateFormat formatter = DateFormat.getDateInstance();
        return formatter.parse(mDate);
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mDate = sdf.format(date);
    }

    public String getColors() {
        return mColors;
    }

    public void setColors(String colors) {
        mColors = colors;
    }

    public String getTextures() {
        return mTextures;
    }

    public void setTextures(String textures) {
        mTextures = textures;
    }

    public String getOccasions() {
        return mOccasions;
    }

    public void setOccasions(String occasions) {
        mOccasions = occasions;
    }

    public String getSeasons() {
        return mSeasons;
    }

    public void setSeasons(String seasons) {
        mSeasons = seasons;
    }

    public String getFit() {
        return mFit;
    }

    public void setFit(String fit) {
        mFit = fit;
    }

    public String getLength() {
        return mLength;
    }

    public void setLength(String length) {
        mLength = length;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }
}
