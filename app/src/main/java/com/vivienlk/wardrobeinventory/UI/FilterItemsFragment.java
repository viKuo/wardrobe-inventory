package com.vivienlk.wardrobeinventory.UI;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.vivienlk.wardrobeinventory.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class FilterItemsFragment extends Fragment {
    @BindView(R.id.itemFilterSpinner) Spinner mItemSpinner;
    @BindView(R.id.itemFilterSpinner) Spinner mColorSpinner;
    @BindView(R.id.itemFilterSpinner) Spinner mSeasonSpinner;

    public FilterItemsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_items, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
