package com.example.propertyapplication;

import androidx.fragment.app.Fragment;

public class PropertyListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new PropertyListFragment();
    }

}
