package com.example.ruslan.mapapplication;

import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ruslan.mapapplication.db.PlaceDataSource;
import com.example.ruslan.mapapplication.models.Place;
import com.example.ruslan.mapapplication.utils.ImageBalloonItem;

import java.util.List;

import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.overlay.balloon.OnBalloonListener;
import ru.yandex.yandexmapkit.utils.GeoPoint;


public class MainActivity extends ActionBarActivity implements OnBalloonListener {
    private PlaceDataSource mPlaceDataSource;
    private MapController mMapController;
    private OverlayManager mOverlayManager;

    private static final GeoPoint DEFAULT_LOCATION = new GeoPoint(55.7903, 49.1347);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlaceDataSource = new PlaceDataSource(this);

        final MapView mapView = (MapView) findViewById(R.id.map);

        mMapController = mapView.getMapController();
        mMapController.setPositionNoAnimationTo(DEFAULT_LOCATION);

        mOverlayManager = mMapController.getOverlayManager();
        // Disable determining the user's location
        mOverlayManager.getMyLocation().setEnabled(false);

        // A simple implementation of map objects
        showPlaces();
    }

    public void showPlaces(){
        // Load required resources
        Resources res = getResources();
        // Create a layer of objects for the map
        Overlay overlay = new Overlay(mMapController);


        List<Place> places = mPlaceDataSource.getAllPlaces();

        for (Place place : places) {
            // Create an object for the layer
            OverlayItem overlayItem = new OverlayItem(new GeoPoint(place.getLatitude(), place.getLongitude()), res.getDrawable(R.drawable.a));
            // Create a balloon model for the object
            ImageBalloonItem balloon = new ImageBalloonItem(this, overlayItem.getGeoPoint());
            balloon.setText(place.getName());
            balloon.setImageUrl(place.getImageUrl());

//            balloon.setOnBalloonListener(this);
            // Add the balloon model to the object
            overlayItem.setBalloonItem(balloon);
            // Add the object to the layer
            overlay.addOverlayItem(overlayItem);
        }

        // Add the layer to the map
        mOverlayManager.addOverlay(overlay);

    }


    @Override
    public void onBalloonViewClick(BalloonItem balloonItem, View view)  {
        Toast.makeText(this, "Здесь можно поставить переход на детальное окно", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBalloonShow(BalloonItem balloonItem) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBalloonHide(BalloonItem balloonItem) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBalloonAnimationStart(BalloonItem balloonItem) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBalloonAnimationEnd(BalloonItem balloonItem) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
