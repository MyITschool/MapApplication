package com.example.ruslan.mapapplication.db;

/**
 * Created by rmansurov on 21.02.2015.
 */
import java.util.ArrayList;
        import java.util.List;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruslan.mapapplication.models.Place;

public class PlaceDataSource extends DataSource {

    // массив полей для получения данных
    private String[] allColumns = {MySQLiteHelper.PLACES_COLUMN_ID,
            MySQLiteHelper.PLACES_COLUMN_NAME, MySQLiteHelper.PLACES_COLUMN_LAT, MySQLiteHelper.PLACES_COLUMN_LONG, MySQLiteHelper.PLACES_COLUMN_IMAGE_URL};

    public PlaceDataSource(Context context) {
        super(context);
    }

    public Place createComment(String name, double latitude, double longitude, String imageUrl) {
        // открываем БД
        super.open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.PLACES_COLUMN_NAME, name);
        values.put(MySQLiteHelper.PLACES_COLUMN_LAT, latitude);
        values.put(MySQLiteHelper.PLACES_COLUMN_LONG, longitude);
        values.put(MySQLiteHelper.PLACES_COLUMN_IMAGE_URL, imageUrl);

        long insertId = database.insert(MySQLiteHelper.TABLE_PLACES, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PLACES,
                allColumns, MySQLiteHelper.PLACES_COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Place newPlace = getPlaceFromCursor(cursor);
        cursor.close();

        // закрываем БД
        super.close();

        return newPlace;
    }

    public void deletePlace(Place place) {
        // открываем БД
        super.open();

        long id = place.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_PLACES, MySQLiteHelper.PLACES_COLUMN_ID
                + " = " + id, null);

        // закрываем БД
        super.close();
    }

    public List<Place> getAllPlaces() {
        // открываем БД
        super.open();

        List<Place> places = new ArrayList<Place>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PLACES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Place place = getPlaceFromCursor(cursor);
            places.add(place);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        // закрываем БД
        super.close();

        return places;
    }

    private Place getPlaceFromCursor(Cursor cursor) {
        Place place = new Place();
        place.setId(cursor.getInt(0));
        place.setName(cursor.getString(1));
        place.setLatitude(cursor.getFloat(2));
        place.setLongitude(cursor.getFloat(3));
        place.setImageUrl(cursor.getString(4));
        return place;
    }
}

