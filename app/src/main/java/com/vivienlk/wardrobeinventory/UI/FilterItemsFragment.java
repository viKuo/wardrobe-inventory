package com.vivienlk.wardrobeinventory.UI;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vivienlk.wardrobeinventory.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FilterItemsFragment extends Fragment {

    public FilterItemsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter_items, container, false);
    }
}
