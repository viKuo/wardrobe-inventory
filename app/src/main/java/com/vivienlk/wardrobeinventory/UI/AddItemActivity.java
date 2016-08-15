package com.vivienlk.wardrobeinventory.UI;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;

import com.vivienlk.wardrobeinventory.R;

public class AddItemActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AddItemActivityFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setVisibility(View.INVISIBLE);
    }
}
