package com.vivienlk.wardrobeinventory.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vivienlk.wardrobeinventory.R;
import com.vivienlk.wardrobeinventory.adapters.WardrobeItemListAdapter;
import com.vivienlk.wardrobeinventory.models.Wardrobe;
import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import java.lang.reflect.Array;
import java.util.List;

import butterknife.BindView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListItemsActivityFragment extends ListFragment {
    //@BindView(R.id.emptyListTextView) TextView mEmptyTextView;
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
        Wardrobe wardrobe = Wardrobe.getInstance(getActivity());
        Intent i = getActivity().getIntent();
        mListView = getListView();
        if (i.getBooleanExtra(FilterItemsFragment.FILTER_BOOLEAN, false)) {
            ((TextView)mListView.getEmptyView()).setText(getText(R.string.no_match_filter));
            String[] filters = {i.getStringExtra(FilterItemsFragment.ITEM_FILTER),
                    i.getStringExtra(FilterItemsFragment.COLOR_FILTER),
                    i.getStringExtra(FilterItemsFragment.SEASON_FILTER),
                    i.getStringExtra(FilterItemsFragment.OCCASION_FILTER)};
            mWardrobeItems = wardrobe.filterGet(filters);
        } else {
            ((TextView)mListView.getEmptyView()).setText(getText(R.string.no_wardrobe_items));
            mWardrobeItems = wardrobe.all();
        }
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
