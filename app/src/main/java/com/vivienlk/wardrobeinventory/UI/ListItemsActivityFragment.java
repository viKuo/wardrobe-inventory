package com.vivienlk.wardrobeinventory.UI;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vivienlk.wardrobeinventory.R;
import com.vivienlk.wardrobeinventory.adapters.WardrobeItemListAdapter;
import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListItemsActivityFragment extends ListFragment {

    public ListItemsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_items, container, false);
        // Construct the data source
        ArrayList<WardrobeItem> arrayOfItems = new ArrayList<WardrobeItem>();

        // Create the adapter to convert the array to views
        WardrobeItemListAdapter adapter = new WardrobeItemListAdapter(this, arrayOfItems);
        // Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.allWardrobeItemsList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
