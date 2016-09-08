package com.vivienlk.wardrobeinventory.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.vivienlk.wardrobeinventory.MultiSpinner;
import com.vivienlk.wardrobeinventory.PictureUtils;
import com.vivienlk.wardrobeinventory.R;
import com.vivienlk.wardrobeinventory.models.Wardrobe;
import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddItemActivityFragment extends Fragment
        implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.photoView) ImageView mPhoto;
    @BindView(R.id.itemInput) Spinner mItem;
    @BindView(R.id.dateInput) EditText mDate;
    @BindView(R.id.colorsInput) EditText mColors;
    @BindView(R.id.texturesInput) EditText mTextures;
    @BindView(R.id.occasionsInput) Spinner mOccasions;
    @BindView(R.id.seasonsInput) MultiSpinner mSeasons;
    @BindView(R.id.lengthInput) Spinner mLength;
    @BindView(R.id.brandInput) EditText mBrand;
    @BindView(R.id.priceInput) EditText mPrice;

    public static final String[] SEASONS = {"Summer", "Fall", "Winter", "Spring"};
    private static final int REQUEST_DATE = 1;
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
        mLength.setAdapter(createAdapter(R.array.length_array));
        mSeasons.setItems(Arrays.asList(SEASONS),
                "", new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
            }
        });
        return view;
    }

    private ArrayAdapter<CharSequence> createAdapter(int array_resource) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                array_resource, android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @OnClick(R.id.saveButton)
    public void saveItem() {
        double price = (isEmpty(mPrice) ? 0 : Double.parseDouble(mPrice.getText().toString()));
        String date = ((isEmpty(mDate)) ? null : mDate.getText().toString());
        String colors = ((isEmpty(mColors)) ? null : mColors.getText().toString());
        String textures = ((isEmpty(mTextures)) ? null : mTextures.getText().toString());
        String brand = ((isEmpty(mBrand)) ? null : mBrand.getText().toString());

        if (mWardrobeItem == null) {
            mWardrobeItem = new WardrobeItem(getContext(), UUID.randomUUID(),
                    mItem.getSelectedItem().toString(), date, colors,
                   textures, mOccasions.getSelectedItem().toString(), mSeasons.getSelectedItem().toString(),
                    mLength.getSelectedItem().toString(), price,
                   brand);
        } else {
            mWardrobeItem.setItem(mItem.getSelectedItem().toString());
            mWardrobeItem.setDate(date);
            mWardrobeItem.setColors(mColors.getText().toString());
            mWardrobeItem.setTextures(mTextures.getText().toString());
            mWardrobeItem.setOccasions(mOccasions.getSelectedItem().toString());
            mWardrobeItem.setSeasons(TextUtils.join(",", mSeasons.getSelectedItems()));
            mWardrobeItem.setLength(mLength.getSelectedItem().toString());
            mWardrobeItem.setPrice(price);
            mWardrobeItem.setBrand(mBrand.getText().toString());
        }
        Wardrobe wardrobe = Wardrobe.getInstance(getActivity());
        wardrobe.save(mWardrobeItem);
        Toast.makeText(getContext(), "Saved!", Toast.LENGTH_LONG).show();
        Intent i = new Intent(getActivity(), ListItemsActivity.class);
        startActivity(i);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mDate.setText((monthOfYear+1) + "/" + dayOfMonth + "/" + year);
        mColors.requestFocus();
    }

    @OnClick(R.id.addPhotoButton)
    public void takePhoto() {
        mWardrobeItem = new WardrobeItem(getActivity(), UUID.randomUUID());
        mPhotoFile = mWardrobeItem.getEmptyPhotoFile();
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mWardrobeItem.setPhotoUri(Uri.fromFile(mPhotoFile));
        i.putExtra(MediaStore.EXTRA_OUTPUT, mWardrobeItem.getPhotoUri());
        startActivityForResult(i, REQUEST_PHOTO);
    }

    @OnFocusChange(R.id.dateInput)
    public void showDatePickerDialog(boolean hasFocus) {
        if (hasFocus) {
            DialogFragment dateFragment = new DatePickerFragment();
            dateFragment.setTargetFragment(this, REQUEST_DATE);
            dateFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
        }
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

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
}
