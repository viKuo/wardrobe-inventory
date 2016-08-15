package com.vivienlk.wardrobeinventory;


import android.os.Bundle;
import android.support.v4.app.Fragment;

public class ListItemsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ListItemsActivityFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
