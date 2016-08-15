package com.vivienlk.wardrobeinventory.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.vivienlk.wardrobeinventory.R;
import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddItemActivityFragment extends Fragment {
    @BindView(R.id.itemInput) Spinner mItem;
    @BindView(R.id.dateInput) EditText mDate;
    @BindView(R.id.colorsInput) EditText mColors;
    @BindView(R.id.texturesInput) EditText mTextures;
    @BindView(R.id.occasionsInput) Spinner mOccasions;
    @BindView(R.id.seasonsInput) Spinner mSeasons;
    @BindView(R.id.fitInput) SeekBar mFit;
    @BindView(R.id.lengthInput) Spinner mLength;
    @BindView(R.id.brandInput) EditText mBrand;
    @BindView(R.id.priceInput) EditText mPrice;


    public AddItemActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        ButterKnife.bind(this, view);

        mItem.setAdapter(createAdapter(R.array.item_types_array));
        mOccasions.setAdapter(createAdapter(R.array.occasion_types_array));
        mSeasons.setAdapter(createAdapter(R.array.seasons_array));
        mLength.setAdapter(createAdapter(R.array.length_array));

        return view;
    }

    private ArrayAdapter<CharSequence> createAdapter(int array_resource) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                array_resource, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @OnClick(R.id.saveButton)
    public void saveItem() {
        double price = Double.parseDouble(mPrice.getText().toString());
        WardrobeItem item = new WardrobeItem(getContext(), UUID.randomUUID(),
                mItem.getSelectedItem().toString(), mDate.getText().toString(), mColors.getText().toString(),
                mTextures.getText().toString(), mOccasions.getSelectedItem().toString(), mSeasons.getSelectedItem().toString(),
                mFit.getProgress() + "", mLength.getSelectedItem().toString(), price,
                mBrand.getText().toString());
        item.save();
        Toast.makeText(getContext(), "Saved!", Toast.LENGTH_LONG).show();
    }
}
