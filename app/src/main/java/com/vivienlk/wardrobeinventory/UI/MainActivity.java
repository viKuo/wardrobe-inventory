package com.vivienlk.wardrobeinventory.UI;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vivienlk.wardrobeinventory.R;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MainActivityFragment();
    }
}
