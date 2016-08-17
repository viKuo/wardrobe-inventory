package com.vivienlk.wardrobeinventory.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vivienlk.wardrobeinventory.R;
import com.vivienlk.wardrobeinventory.adapters.WardrobeItemListAdapter;
import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListItemsActivityFragment extends ListFragment {
    ListView mListView;
    WardrobeItemListAdapter mAdapter;
    List<WardrobeItem> mWardrobeItems;


    public ListItemsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_items, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mListView = getListView();
        WardrobeItem item = new WardrobeItem(getContext());
        mWardrobeItems = item.all();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WardrobeItem data = mWardrobeItems.get(position);
                Intent i = new Intent(getContext(), SingleItemActivity.class);
                i.putExtra("wardrobeItem", data);
                startActivity(i);
            }
        });
        updateUI();
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new WardrobeItemListAdapter(getContext(), mWardrobeItems);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.setItems(mWardrobeItems);
            mAdapter.notifyDataSetChanged();
        }
    }

}
