package com.example.propertyapplication;

import java.util.UUID;

public class Property {

    private UUID mId;
    private String mAddress;
    private String mSuburb;
    private String mState;
    private int mPostCode;
    private String mSalePrice;

    public Property() {
        this(UUID.randomUUID());
    }

    public Property(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getSuburb() {
        return mSuburb;
    }

    public void setSuburb(String suburb) {
        mSuburb = suburb;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public int getPostCode() {
        return mPostCode;
    }

    public void setPostCode(int postCode) {
        mPostCode = postCode;
    }

    public String getSalePrice() {
        return mSalePrice;
    }

    public void setSalePrice(String salePrice) {
        mSalePrice = salePrice;
    }
}
