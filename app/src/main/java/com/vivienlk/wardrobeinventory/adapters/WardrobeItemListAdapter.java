package com.vivienlk.wardrobeinventory.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import java.util.ArrayList;

/**
 * Created by Vivien on 8/14/2016.
 */
public class WardrobeItemListAdapter extends ArrayAdapter<WardrobeItem>{

    public WardrobeItemListAdapter(Context context, int layoutResourceId, ArrayList<WardrobeItem> items) {
        super(context, layoutResourceId, items);

    }
}
