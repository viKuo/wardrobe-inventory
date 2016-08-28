package com.vivienlk.wardrobeinventory.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.vivienlk.wardrobeinventory.database.DatabaseHelper;

import com.vivienlk.wardrobeinventory.database.WardrobeDbSchema.WardrobeTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Vivien on 8/14/2016.
 */
public class WardrobeItem implements Parcelable{
    private Context mContext;
    private Uri mPhotoUri;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mItem);
        dest.writeString(mPhotoUri.toString());
        dest.writeString(mDate);
        dest.writeString(mColors);
        dest.writeString(mTextures);
        dest.writeString(mOccasions);
        dest.writeString(mSeasons);
        dest.writeString(mFit);
        dest.writeString(mLength);
        dest.writeString(mPrice + "");
        dest.writeString(mBrand);
    }

    private WardrobeItem(Parcel in) {
        mItem = in.readString();
        mPhotoUri = Uri.parse(in.readString());
        mDate = in.readString();
        mColors = in.readString();
        mTextures = in.readString();
        mOccasions = in.readString();
        mSeasons = in.readString();
        mFit = in.readString();
        mLength = in.readString();
        mPrice = Double.parseDouble(in.readString());
        mBrand = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<WardrobeItem> CREATOR
            = new Parcelable.Creator<WardrobeItem>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public WardrobeItem createFromParcel(Parcel in) {
            return new WardrobeItem(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public WardrobeItem[] newArray(int size) {
            return new WardrobeItem[size];
        }
    };

    public File getEmptyPhotoFile() {
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }

        return new File(externalFilesDir, getPhotoFilename());
    }

    public boolean hasPhotoUri() {
        if(mPhotoUri != null) {
            return true;
        } else {
            return false;
        }
    }

    public Bitmap getPhoto() {
        try {
            File f = new File(mPhotoUri.getPath());
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPhotoFilename() {
        return "IMG_" + mId + ".jpg";
    }


    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
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

    public String getDateString() {
        return mDate;
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

    public Uri getPhotoUri() {
        return mPhotoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        mPhotoUri = photoUri;
    }

}
