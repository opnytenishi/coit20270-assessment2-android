package com.example.propertyapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.propertyapplication.database.PropertyBaseHelper;
import com.example.propertyapplication.database.PropertyCursorWrapper;
import com.example.propertyapplication.database.PropertyDbSchema.PropertyTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PropertyLab {

    private static PropertyLab sPropertyLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PropertyLab get(Context context) {
        if (sPropertyLab == null) {
            sPropertyLab = new PropertyLab(context);
        }
        return sPropertyLab;
    }

    private PropertyLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PropertyBaseHelper(mContext).getWritableDatabase();

        List<Property> properties = getProperties();
        if (properties.isEmpty()) {
            properties = getDummyProperties();
            for (Property prop : properties) {
                addProperty(prop);
            }
        }
    }

    public void addProperty(Property c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(PropertyTable.NAME, null, values);
    }

    public List<Property> getProperties() {
        List<Property> properties = new ArrayList<>();

        try (PropertyCursorWrapper cursor = queryCrimes(null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                properties.add(cursor.getProperty());
                cursor.moveToNext();
            }
        }

//        if (properties.isEmpty()) {
//            properties.addAll(getDummyProperties());
//        }
        return properties;
    }

    public Property getProperty(UUID id) {

        try (PropertyCursorWrapper cursor = queryCrimes(PropertyTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        )) {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getProperty();
        }
    }

    public void updateProperty(Property property) {
        String uuidString = property.getId().toString();
        ContentValues values = getContentValues(property);
        mDatabase.update(PropertyTable.NAME, values, PropertyTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private PropertyCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                PropertyTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new PropertyCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Property property) {
        ContentValues values = new ContentValues();
        values.put(PropertyTable.Cols.UUID, property.getId().toString());
        values.put(PropertyTable.Cols.ADDRESS, property.getAddress());
        values.put(PropertyTable.Cols.SUBURB, property.getSuburb());
        values.put(PropertyTable.Cols.STATE, property.getState());
        values.put(PropertyTable.Cols.POSTCODE, property.getPostCode());
        values.put(PropertyTable.Cols.SALEPRICE, property.getSalePrice());

        return values;
    }

    private List<Property> getDummyProperties() {

        List<Property> properties = new ArrayList<>();

        Property property1 = new Property();
        property1.setAddress("360 Pier Point Road");
        property1.setSuburb("Cairns");
        property1.setState("QLD");
        property1.setPostCode(4870);
        property1.setSalePrice(200000);
        properties.add(property1);

        Property property2 = new Property();
        property2.setAddress("250 Sheridan St");
        property2.setSuburb("Cairns");
        property2.setState("QLD");
        property2.setPostCode(4870);
        property2.setSalePrice(350000);
        properties.add(property2);

        Property property3 = new Property();
        property3.setAddress("86 Taylor St");
        property3.setSuburb("Trinity Beach");
        property3.setState("QLD");
        property3.setPostCode(4870);
        property3.setSalePrice(800000);
        properties.add(property3);

        Property property4 = new Property();
        property4.setAddress("17 Martin St");
        property4.setSuburb("Cairns");
        property4.setState("QLD");
        property4.setPostCode(4870);
        property4.setSalePrice(550000);
        properties.add(property4);

        Property property5 = new Property();
        property5.setAddress("715 Mulgrave Rd");
        property5.setSuburb("Earlville");
        property5.setState("QLD");
        property5.setPostCode(4870);
        property5.setSalePrice(400000);
        properties.add(property5);

        return properties;
    }
}
