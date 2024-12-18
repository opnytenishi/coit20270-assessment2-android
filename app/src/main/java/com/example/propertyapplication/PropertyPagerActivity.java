package com.example.propertyapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class PropertyPagerActivity extends AppCompatActivity {

    private static final String EXTRA_PROPERTY_ID = "com.example.propertyapplication.property_id";

    private ViewPager mViewPager;
    private List<Property> mProperties;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, PropertyPagerActivity.class);
        intent.putExtra(EXTRA_PROPERTY_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_PROPERTY_ID);

        mViewPager = (ViewPager) findViewById(R.id.property_view_pager);

        mProperties = PropertyLab.get(this).getProperties();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Property property = mProperties.get(position);
                return PropertyFragment.newInstance(property.getId());
            }

            @Override
            public int getCount() {
                return mProperties.size();
            }
        });

        for (int i = 0; i < mProperties.size(); i++) {
            if (mProperties.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
