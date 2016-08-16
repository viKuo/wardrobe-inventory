package com.vivienlk.wardrobeinventory.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivienlk.wardrobeinventory.R;
import com.vivienlk.wardrobeinventory.UI.ListItemsActivityFragment;
import com.vivienlk.wardrobeinventory.models.WardrobeItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vivien on 8/14/2016.
 */
public class WardrobeItemListAdapter extends ArrayAdapter<WardrobeItem>{

    public WardrobeItemListAdapter(Context context, ArrayList<WardrobeItem> items) {
        super(context, 0, items);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WardrobeItem item = getItem(position);
        ViewHolder viewHolder;
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wardrobe_list_item, parent, false);
            viewHolder = new ViewHolder(getContext(), convertView);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.mItemView.setText(item.getItem());
        viewHolder.mOccasionView.setText(item.getOccasions());
        viewHolder.mColorsView.setText(item.getColors());
        viewHolder.mSeasonView.setText(item.getSeasons());
        // Return the completed view to render on screen
        return convertView;
    }

    private class ViewHolder {
        @BindView(R.id.itemImageView) ImageView mImageView;
        @BindView(R.id.itemTextView) TextView mItemView;
        @BindView(R.id.occasionTextView) TextView mOccasionView;
        @BindView(R.id.seasonTextView) TextView mSeasonView;
        @BindView(R.id.colorsTextView) TextView mColorsView;

        public ViewHolder(Context context, View view) {
            ButterKnife.bind(getContext(), view);
        }

    }
}
