package com.vivienlk.wardrobeinventory.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.vivienlk.wardrobeinventory.PictureUtils;
import com.vivienlk.wardrobeinventory.R;
import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddItemActivityFragment extends Fragment {
    @BindView(R.id.photoView) ImageView mPhoto;
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

    private static final int REQUEST_PHOTO = 2;
    private WardrobeItem mWardrobeItem;
    private File mPhotoFile;


    public AddItemActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        ButterKnife.bind(this, view);

        //for spinner dropdown menu
        mItem.setAdapter(createAdapter(R.array.item_types_array));
        mOccasions.setAdapter(createAdapter(R.array.occasion_types_array));
        mSeasons.setAdapter(createAdapter(R.array.seasons_array));
        mLength.setAdapter(createAdapter(R.array.length_array));


        return view;
    }

    private ArrayAdapter<CharSequence> createAdapter(int array_resource) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                array_resource, android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @OnClick(R.id.saveButton)
    public void saveItem() {
        double price = Double.parseDouble(mPrice.getText().toString());
        if (mWardrobeItem == null) {
            WardrobeItem item = new WardrobeItem(getContext(), UUID.randomUUID(),
                    mItem.getSelectedItem().toString(), mDate.getText().toString(), mColors.getText().toString(),
                    mTextures.getText().toString(), mOccasions.getSelectedItem().toString(), mSeasons.getSelectedItem().toString(),
                    mFit.getProgress() + "", mLength.getSelectedItem().toString(), price,
                    mBrand.getText().toString());
        } else {
            mWardrobeItem.setItem(mItem.getSelectedItem().toString());
            mWardrobeItem.setDate(new Date());
            mWardrobeItem.setColors(mColors.getText().toString());
            mWardrobeItem.setTextures(mTextures.getText().toString());
            mWardrobeItem.setOccasions(mOccasions.getSelectedItem().toString());
            mWardrobeItem.setSeasons(mSeasons.getSelectedItem().toString());
            mWardrobeItem.setFit(mFit.getProgress() + "");
            mWardrobeItem.setLength(mLength.getSelectedItem().toString());
            mWardrobeItem.setPrice(price);
            mWardrobeItem.setBrand(mBrand.getText().toString());
        }
        mWardrobeItem.save();
        Toast.makeText(getContext(), "Saved!", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getActivity(), ListItemsActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.addPhotoButton)
    public void takePhoto() {
        mWardrobeItem = new WardrobeItem(getContext());
        mPhotoFile = mWardrobeItem.getEmptyPhotoFile();
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mWardrobeItem.setPhotoUri(Uri.fromFile(mPhotoFile));
        i.putExtra(MediaStore.EXTRA_OUTPUT, mWardrobeItem.getPhotoUri());
        startActivityForResult(i, REQUEST_PHOTO);
    }

    private void updatePhotoView() {
        if (mPhotoFile != null && mPhotoFile.exists()) {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhoto.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
    }
}
