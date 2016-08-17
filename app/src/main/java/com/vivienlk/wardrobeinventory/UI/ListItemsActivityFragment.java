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

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListItemsActivityFragment extends ListFragment {
    ListView mListView;
    WardrobeItemListAdapter mAdapter;

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
        updateUI();
    }

    private void updateUI() {
        WardrobeItem item = new WardrobeItem(getContext());
        List<WardrobeItem> wardrobeItemList = item.all();
        if (mAdapter == null) {
            mAdapter = new WardrobeItemListAdapter(getContext(), wardrobeItemList);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.setItems(wardrobeItemList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
