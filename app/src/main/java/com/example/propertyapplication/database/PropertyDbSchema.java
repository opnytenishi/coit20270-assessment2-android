package com.example.propertyapplication.database;

public class PropertyDbSchema {
    public static final class PropertyTable {
        public static final String NAME = "properties";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String ADDRESS = "address";
            public static final String SUBURB = "Suburb";
            public static final String STATE = "state";
            public static final String POSTCODE = "postcode";
            public static final String SALEPRICE = "saleprice";
        }
    }
}
