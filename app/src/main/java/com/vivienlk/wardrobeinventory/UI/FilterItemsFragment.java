package com.vivienlk.wardrobeinventory.UI;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.vivienlk.wardrobeinventory.R;
import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class FilterItemsFragment extends Fragment {
    @BindView(R.id.itemFilterSpinner) Spinner mItemSpinner;
    @BindView(R.id.colorFilterSpinner) Spinner mColorSpinner;
    @BindView(R.id.seasonFilterSpinner) Spinner mSeasonSpinner;

    public static final String ITEM_FILTER = "itemFilter";
    public static final String COLOR_FILTER = "colorFilter";
    public static final String SEASON_FILTER = "seasonFilter";
    public static final String FILTER_BOOLEAN = "filterBoolean";

    public FilterItemsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_items, container, false);
        ButterKnife.bind(this, view);

        WardrobeItem item = new WardrobeItem(getContext());

        mItemSpinner.setAdapter(createAdapter(R.array.item_types_array));
        mColorSpinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, item.getAllColors()));
        mSeasonSpinner.setAdapter(createAdapter(R.array.seasons_array));
        return view;
    }

    private ArrayAdapter<CharSequence> createAdapter(int array_resource) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                array_resource, android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @OnClick(R.id.filterButton) void filterList() {
        Intent i = new Intent(getContext(), ListItemsActivity.class);
        i.putExtra(FILTER_BOOLEAN, true);
        i.putExtra(ITEM_FILTER, mItemSpinner.getSelectedItem().toString());
        String colorFilter = "%" + mColorSpinner.getSelectedItem().toString() + "%";
        i.putExtra(COLOR_FILTER, colorFilter);
        i.putExtra(SEASON_FILTER, mSeasonSpinner.getSelectedItem().toString());
        startActivity(i);
    }
}
