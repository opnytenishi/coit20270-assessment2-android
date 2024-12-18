package com.example.propertyapplication.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.propertyapplication.Property;
import com.example.propertyapplication.database.PropertyDbSchema.PropertyTable;

import java.util.UUID;

public class PropertyCursorWrapper extends CursorWrapper {

    public PropertyCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Property getProperty() {
        String uuidString = getString(getColumnIndex(PropertyTable.Cols.UUID));
        String address = getString(getColumnIndex(PropertyTable.Cols.ADDRESS));
        String suburb = getString(getColumnIndex(PropertyTable.Cols.SUBURB));
        String state = getString(getColumnIndex(PropertyTable.Cols.STATE));
        int postCode = getInt(getColumnIndex(PropertyTable.Cols.POSTCODE));
        int salePrice = getInt(getColumnIndex(PropertyTable.Cols.SALEPRICE));

        Property property = new Property(UUID.fromString(uuidString));
        property.setAddress(address);
        property.setSuburb(suburb);
        property.setState(state);
        property.setPostCode(postCode);
        property.setSalePrice(salePrice);

        return property;
    }
}
