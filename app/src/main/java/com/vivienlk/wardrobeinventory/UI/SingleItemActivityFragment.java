package com.vivienlk.wardrobeinventory.UI;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivienlk.wardrobeinventory.R;
import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class SingleItemActivityFragment extends Fragment {
    @BindView(R.id.photo) ImageView mImageView;
    @BindView(R.id.itemInputTextView) TextView mItemView;
    @BindView(R.id.dateInputTextView) TextView mDateView;
    @BindView(R.id.colorsInputTextView) TextView mColorsView;
    @BindView(R.id.texturesInputTextView) TextView mTexturesView;
    @BindView(R.id.occasionsInputTextView) TextView mOccasionsView;
    @BindView(R.id.seasonsInputTextView) TextView mSeasonsView;
    @BindView(R.id.fitInputTextView) TextView mFitView;
    @BindView(R.id.lengthInputTextView) TextView mLengthView;
    @BindView(R.id.brandInputTextView) TextView mBrandView;
    @BindView(R.id.priceInputTextView) TextView mPriceView;
    WardrobeItem mItem;

    public SingleItemActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_item, container, false);
        mItem = (WardrobeItem) getActivity().getIntent().getParcelableExtra("wardrobeItem");
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mItemView.setText(mItem.getItem());
        mDateView.setText(mItem.getDateString());
        mColorsView.setText(mItem.getColors());
        mTexturesView.setText(mItem.getTextures());
        mOccasionsView.setText(mItem.getOccasions());
        mSeasonsView.setText(mItem.getSeasons());
        mFitView.setText(mItem.getFit());
        mLengthView.setText(mItem.getLength());
        mBrandView.setText(mItem.getBrand());
        mPriceView.setText(mItem.getPrice() + "");
    }
}
