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

import com.vivienlk.wardrobeinventory.database.DatabaseHelper;

import com.vivienlk.wardrobeinventory.database.WardrobeDbSchema.WardrobeTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Vivien on 8/14/2016.
 */
public class WardrobeItem implements Parcelable{
    private Context mContext;
    private SQLiteDatabase mDatabase;
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

    public WardrobeItem (Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext)
                .getWritableDatabase();
        mId = UUID.randomUUID();
    }

    private ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(WardrobeTable.Cols.UUID, mId.toString());
        values.put(WardrobeTable.Cols.PHOTOURI, mPhotoUri.getPath());
        values.put(WardrobeTable.Cols.ITEM, mItem);
        values.put(WardrobeTable.Cols.DATE, mDate);
        values.put(WardrobeTable.Cols.COLORS, mColors);
        values.put(WardrobeTable.Cols.TEXTURES, mTextures);
        values.put(WardrobeTable.Cols.OCCASIONS, mOccasions);
        values.put(WardrobeTable.Cols.SEASONS, mSeasons);
        values.put(WardrobeTable.Cols.FIT, mFit);
        values.put(WardrobeTable.Cols.LENGTH, mLength);
        values.put(WardrobeTable.Cols.PRICE, mPrice);
        values.put(WardrobeTable.Cols.BRAND, mBrand);
        return values;
    }

    public void save() {
        ContentValues values = getContentValues();
        mDatabase.insert(WardrobeTable.NAME, null, values);
    }

    public void update() {
        ContentValues values = getContentValues();
        mDatabase.update(WardrobeTable.NAME,
                values,
                WardrobeTable.Cols.UUID + " = ?",
                new String[] { mId.toString() });
    }

    public WardrobeItem getWardrobeItem(UUID id) {
        WardrobeCursorWrapper cursor = queryItem(WardrobeTable.Cols.UUID + " = ?",
                new String[] { id.toString() });

        try {
            if (cursor.getCount() == 0 ) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getWardrobeItem();
        } finally {
            cursor.close();
        }
    }

    public List<WardrobeItem> all() {
        List<WardrobeItem> items = new ArrayList<>();
        WardrobeCursorWrapper cursor = queryItem(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                items.add(cursor.getWardrobeItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    private WardrobeCursorWrapper queryItem (String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                WardrobeTable.NAME,
                null, // null selects all columns
                whereClause,
                whereArgs,
                null, //group by
                null, //having
                null //order by
        );
        return new WardrobeCursorWrapper(cursor, mContext);
    }

    private class WardrobeCursorWrapper extends CursorWrapper {
        private Context mContext;
        public WardrobeCursorWrapper(Cursor cursor, Context context) {
            super(cursor);
            mContext = context;
        }

        public WardrobeItem getWardrobeItem() {
            UUID uuidString = UUID.fromString(getString(getColumnIndex(WardrobeTable.Cols.UUID)));
            Uri uri = Uri.parse(getString(getColumnIndex(WardrobeTable.Cols.PHOTOURI)));
            String item  = getString(getColumnIndex(WardrobeTable.Cols.ITEM));
            String date = getString(getColumnIndex(WardrobeTable.Cols.DATE));
            String colors = getString(getColumnIndex(WardrobeTable.Cols.COLORS));
            String textures = getString(getColumnIndex(WardrobeTable.Cols.TEXTURES));
            String occasions = getString(getColumnIndex(WardrobeTable.Cols.OCCASIONS));
            String seasons = getString(getColumnIndex(WardrobeTable.Cols.SEASONS));
            String fit = getString(getColumnIndex(WardrobeTable.Cols.FIT));
            String length = getString(getColumnIndex(WardrobeTable.Cols.LENGTH));
            double price  = Double.parseDouble(getString(getColumnIndex(WardrobeTable.Cols.PRICE)));
            String brand = getString(getColumnIndex(WardrobeTable.Cols.BRAND));

            WardrobeItem wardrobeItem = new WardrobeItem(mContext, uuidString, item,
                    date, colors, textures, occasions,
                    seasons, fit, length, price, brand);
            wardrobeItem.setPhotoUri(uri);
            return wardrobeItem;
        }
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
