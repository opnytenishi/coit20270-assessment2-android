package com.example.propertyapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class PropertyFragment extends Fragment {

    private static final String ARG_PROPERTY_ID = "property_id";

    private Property mProperty;
//    private EditText mAddressField;
//    private EditText mSuburbField;
//    private EditText mStateField;
//    private EditText mPostCodeField;
//    private EditText mSalePriceField;

    public static PropertyFragment newInstance(UUID propertyId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROPERTY_ID, propertyId);

        PropertyFragment fragment = new PropertyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID propertyId = (UUID) getArguments().getSerializable(ARG_PROPERTY_ID);
        mProperty = PropertyLab.get(getActivity()).getProperty(propertyId);
    }

    @Override
    public void onPause() {
        super.onPause();
        PropertyLab.get(getActivity()).updateProperty(mProperty);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_property, container, false);

//        mAddressField = (EditText) v.findViewById(R.id.crime_title);
//        mAddressField.setText(mProperty.getAddress());
//        mAddressField.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mProperty.setAddress(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        return v;
    }

}