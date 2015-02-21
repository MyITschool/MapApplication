package com.example.ruslan.mapapplication.db;

/**
 * Created by rmansurov on 21.02.2015.
 */
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MySQLiteHelper extends SQLiteAssetHelper {

    public static final String TABLE_PLACES = "places";
    public static final String PLACES_COLUMN_ID = "id";
    public static final String PLACES_COLUMN_NAME = "name";
    public static final String PLACES_COLUMN_LAT = "lat";
    public static final String PLACES_COLUMN_LONG = "long";
    public static final String PLACES_COLUMN_IMAGE_URL = "image_url";

    private static final String DATABASE_NAME = "geo.db";
    private static final int DATABASE_VERSION = 1;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
