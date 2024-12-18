package com.example.propertyapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.propertyapplication.database.PropertyDbSchema.PropertyTable;

public class PropertyBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "propertyBase.db";

    public PropertyBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PropertyTable.NAME + "(" +
                " _id integer PRIMARY KEY AUTOINCREMENT, " +
                PropertyTable.Cols.UUID + ", " +
                PropertyTable.Cols.ADDRESS + ", " +
                PropertyTable.Cols.SUBURB + ", " +
                PropertyTable.Cols.STATE + ", " +
                PropertyTable.Cols.POSTCODE + ", " +
                PropertyTable.Cols.SALEPRICE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
