package com.example.propertyapplication;

import static android.widget.Toast.*;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Objects;
import java.util.UUID;

public class PropertyFragment extends Fragment {

    private static final String ARG_PROPERTY_ID = "property_id";
    private boolean isNewProperty;

    private Property mProperty;
    private EditText mAddressField;
    private EditText mSuburbField;
    private EditText mStateField;
    private EditText mPostCodeField;
    private EditText mSalePriceField;
    private Button mNewPropertyButton;
    private Button mSaveButton;

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
        isNewProperty = getActivity().getIntent().getBooleanExtra(
                PropertyPagerActivity.EXTRA_NEW_PROPERTY, false);

        if (isNewProperty) {
            mProperty = new Property();
        } else {
            UUID propertyId = (UUID) getArguments().getSerializable(ARG_PROPERTY_ID);
            mProperty = PropertyLab.get(getActivity()).getProperty(propertyId);
        }
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

        mAddressField = (EditText) v.findViewById(R.id.property_address);
        mAddressField.setText(mProperty.getAddress());
        mAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProperty.setAddress(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSuburbField = (EditText) v.findViewById(R.id.property_suburb);
        mSuburbField.setText(mProperty.getSuburb());
        mSuburbField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProperty.setSuburb(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mStateField = (EditText) v.findViewById(R.id.property_state);
        mStateField.setText(mProperty.getState());
        mStateField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProperty.setState(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPostCodeField = (EditText) v.findViewById(R.id.property_postcode);
        mPostCodeField.setText(String.valueOf(mProperty.getPostCode()));
        mPostCodeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    mProperty.setPostCode(Integer.parseInt(s.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSalePriceField = (EditText) v.findViewById(R.id.property_sale_price);
        mSalePriceField.setText(String.valueOf(mProperty.getSalePrice()));
        mSalePriceField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProperty.setSalePrice(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNewPropertyButton = (Button) v.findViewById(R.id.new_property_btn);
        mNewPropertyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = PropertyPagerActivity.newIntent(
                        getActivity(), null, true);
                startActivity(intent);
            }
        });


        mSaveButton = (Button) v.findViewById(R.id.save_btn);
        mSaveButton.setVisibility(View.GONE);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mAddressField.getText().toString().isEmpty() || mSuburbField.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), R.string.empty_address_suburb_error, Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                try {
                    int salePrice = Integer.parseInt(mSalePriceField.getText().toString());
                    if (salePrice < 1000) {
                        Toast.makeText(getActivity(), R.string.minimum_sale_price_error, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), R.string.integer_sale_price_error, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isNewProperty) {
                    PropertyLab.get(getActivity()).addProperty(mProperty);

                    Intent intent = new Intent(getActivity(), PropertyListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                requireActivity().finish();
            }
        });

        if (isNewProperty) {
            mSaveButton.setVisibility(View.VISIBLE);
            mNewPropertyButton.setVisibility(View.GONE);
        } else {
            mSaveButton.setVisibility(View.GONE);
            mNewPropertyButton.setVisibility(View.VISIBLE);
        }

        return v;
    }

}
