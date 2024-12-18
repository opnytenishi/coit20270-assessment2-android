package com.example.propertyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PropertyListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private PropertyAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.property_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        PropertyLab propertyLab = PropertyLab.get(getActivity());
        List<Property> properties = propertyLab.getProperties();

        if (mAdapter == null) {
            mAdapter = new PropertyAdapter(properties);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setProperties(properties);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class PropertyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mAddressTextView;
        private TextView mSalePriceTextView;
        private Property mProperty;

        public PropertyHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_property, parent, false));
            itemView.setOnClickListener(this);

            mAddressTextView = (TextView) itemView.findViewById(R.id.property_full_address);
            mSalePriceTextView = (TextView) itemView.findViewById(R.id.property_sale_price);
        }
        @Override
        public void onClick(View view) {
            Intent intent = PropertyPagerActivity.newIntent(getActivity(), mProperty.getId(), false);
            startActivity(intent);
        }

        public void bind(Property property) {
            String fullAddress = "";
            mProperty = property;
            fullAddress = mProperty.getAddress() + ", " + mProperty.getSuburb() +
                    ", " + mProperty.getState() + " " + mProperty.getPostCode();
            mAddressTextView.setText(fullAddress);
            mSalePriceTextView.setText(
                    getString(R.string.property_sale_price_format, mProperty.getSalePrice()));
        }
    }

    private class PropertyAdapter extends RecyclerView.Adapter<PropertyHolder> {

        private List<Property> mProperties;

        public PropertyAdapter(List<Property> properties) {
            mProperties = properties;
        }

        @NonNull
        @Override
        public PropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new PropertyHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PropertyHolder holder, int position) {
            Property property = mProperties.get(position);
            holder.bind(property);
        }

        @Override
        public int getItemCount() {
            return mProperties.size();
        }

        public void setProperties(List<Property> properties) {
            mProperties = properties;
        }

    }

}
