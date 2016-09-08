package com.vivienlk.wardrobeinventory.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.vivienlk.wardrobeinventory.database.DatabaseHelper;
import com.vivienlk.wardrobeinventory.database.WardrobeDbSchema;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Vivien on 8/25/2016.
 */
public class Wardrobe {
    private static Wardrobe sWardrobe;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static Wardrobe getInstance(Context context) {
        if (sWardrobe == null) {
            sWardrobe = new Wardrobe(context);
        }
        return sWardrobe;
    }

    private Wardrobe(Context context) {
        mContext = context;
        mDatabase = new DatabaseHelper(mContext)
                .getWritableDatabase();
    }

    //database methods
    private WardrobeCursorWrapper queryItem (String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                WardrobeDbSchema.WardrobeTable.NAME,
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
            UUID uuidString = UUID.fromString(getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.UUID)));
            String item  = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.ITEM));
            String date = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.DATE));
            String colors = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.COLORS));
            String textures = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.TEXTURES));
            String occasions = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.OCCASIONS));
            String seasons = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.SEASONS));
            String fit = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.FIT));
            String length = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.LENGTH));
            double price  = Double.parseDouble(getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.PRICE)));
            String brand = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.BRAND));
            String uriString = getString(getColumnIndex(WardrobeDbSchema.WardrobeTable.Cols.PHOTOURI));
            Uri uri;
            if (uriString == null) {
                uri = null;
            } else {
                uri = Uri.parse(uriString);
            }

            WardrobeItem wardrobeItem = new WardrobeItem(mContext, uuidString, item,
                    date, colors, textures, occasions,
                    seasons, length, price, brand);

            if (uri != null) {
                wardrobeItem.setPhotoUri(uri);
            }
            return wardrobeItem;
        }
    }


    public void save(WardrobeItem item) {
        ContentValues values = getContentValues(item);
        mDatabase.insert(WardrobeDbSchema.WardrobeTable.NAME, null, values);
    }

    public void update(WardrobeItem item) {
        ContentValues values = getContentValues(item);
        mDatabase.update(WardrobeDbSchema.WardrobeTable.NAME,
                values,
                WardrobeDbSchema.WardrobeTable.Cols.UUID + " = ?",
                new String[] { item.getId().toString() });
    }

    private ContentValues getContentValues(WardrobeItem item) {
        ContentValues values = new ContentValues();
        values.put(WardrobeDbSchema.WardrobeTable.Cols.UUID, item.getId().toString());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.PHOTOURI, item.getPhotoUriPath());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.ITEM, item.getItem());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.DATE, item.getDateString());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.COLORS, item.getColors());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.TEXTURES, item.getTextures());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.OCCASIONS, item.getOccasions());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.SEASONS, item.getSeasons());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.LENGTH, item.getLength());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.PRICE, item.getPrice());
        values.put(WardrobeDbSchema.WardrobeTable.Cols.BRAND, item.getBrand());
        return values;
    }

    public WardrobeItem getWardrobeItem(UUID id) {
        WardrobeCursorWrapper cursor = queryItem(WardrobeDbSchema.WardrobeTable.Cols.UUID + " = ?",
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

    public List<WardrobeItem> filterGet(String[] filters) {
        List<WardrobeItem> items = new ArrayList<>();
        String[] queryArgs = queryArgs(filters);
        String[] filterArgs;
        if (queryArgs[0].equals("")) {
            queryArgs[0] = null;
            filterArgs = null;
        } else {
           filterArgs = queryArgs[1].split(", ");
        }
        WardrobeCursorWrapper cursor = queryItem(queryArgs[0], filterArgs);
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

    private String[] queryArgs(String[] filters) {
        String[] possibleWhereArgs = {"item = ?", "colors LIKE ?", "seasons LIKE ?", "occasions = ?"};
        List<String> whereArgs = new ArrayList<String>();
        List<String> filterArgs = new ArrayList<String>();
        Log.d("filters", TextUtils.join(", ", filters));
        for (int i = 0; i < possibleWhereArgs.length; i++) {
            if (!filters[i].equals("") && !filters[i].equals("%%")) {
                whereArgs.add(possibleWhereArgs[i]);
                filterArgs.add(filters[i]);
            }
        }
        String[] queryArgs = {TextUtils.join(" AND ", whereArgs), TextUtils.join(",", filterArgs)};
        return queryArgs;
    }


    public List<String> getAllColors() {
        Set<String> colorsSet = new HashSet<>();
        List<WardrobeItem> items = all();
        colorsSet.add("");
        for (WardrobeItem item : items) {
            List<String> itemColors = Arrays.asList(item.getColors().split(", "));
            colorsSet.addAll(itemColors);
        }
        List<String> colors = new ArrayList<>(colorsSet);
        Collections.sort(colors);
        return colors;
    }
}
