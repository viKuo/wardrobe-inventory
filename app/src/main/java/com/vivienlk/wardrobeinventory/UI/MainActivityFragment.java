package com.vivienlk.wardrobeinventory.UI;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vivienlk.wardrobeinventory.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        return view;

    }

    @OnClick(R.id.add_new_item_button) void addItem() {
        Intent i = new Intent(getActivity(), AddItemActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.view_all_items_button) void listItems() {
        Intent i = new Intent(getActivity(), ListItemsActivity.class);
        startActivity(i);

    }

    public interface onFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
