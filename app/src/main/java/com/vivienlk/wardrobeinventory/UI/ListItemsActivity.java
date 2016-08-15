package com.vivienlk.wardrobeinventory.UI;


import android.support.v4.app.Fragment;

public class ListItemsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ListItemsActivityFragment();
    }

}
