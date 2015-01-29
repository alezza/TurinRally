package com.example.al.turinrally;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.al.challenges.*;
import com.example.al.challenges.CompositeChallenge;
import com.example.al.turinrally.Challenge1.*;
import com.example.al.turinrally.Challenge2.*;
import com.example.al.turinrally.Challenge3.*;
import com.example.al.turinrally.Challenge4.Challenge4Activity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MainActivity extends Activity {
    static final LatLng TURIN = new LatLng(45.070312, 7.686856);
    static final LatLng START = new LatLng(45.071412, 7.685223);
    static final LatLng MOLEA = new LatLng(45.069025, 7.693235);
    static final LatLng GRANM = new LatLng(45.062578, 7.699436);

    private GoogleMap map;
    private Polyline mapPolyLine;
    static final LatLng INIT = new LatLng(45, 7.6);
    private Marker currentLocation;
    int stage;
    final Context context = this;
    Marker start;
    Marker turin;
    Marker mole;
    Marker gran;
    Polyline path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final CompositeChallenge compositeChallenge = new CompositeChallenge();
        compositeChallenge.add(new Challenge1());
        compositeChallenge.add(new Challenge2());
        compositeChallenge.add(new Challenge3());
        compositeChallenge.add(new Challenge4());
        compositeChallenge.add(new FinalMystery());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = null;
                if(marker.getPosition().latitude==45.071412 && marker.getPosition().longitude==7.685223) {
                   intent = new Intent(context, Challenge1Activity.class);
                }
                if (marker.getPosition().latitude==45.069025 && marker.getPosition().longitude==7.693235){
                    intent = new Intent(context, Challenge3Activity.class);
                }

                if (marker.getPosition().latitude==45.062578 && marker.getPosition().longitude==7.699436){
                    intent = new Intent(context, Challenge4Activity.class);
                }

                Bundle bundle = new Bundle();

                bundle.putString("challange", compositeChallenge.getRandomChallenge().toString());

                intent.putExtras(bundle);

                startActivity(intent);
                return true;
            }
        });

        turin = map.addMarker(new MarkerOptions().position(TURIN)
                .title("Turin"));

        start = map.addMarker(new MarkerOptions()
                .position(START)
                .title("Start")
                .snippet("Start Point is Here - Piazza Castello")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.symbol)));
        start.showInfoWindow();

        mole = map.addMarker(new MarkerOptions()
                .position(MOLEA)
                .title("Mole Antonelliana")
                .snippet("Second Point of Interest")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.symbol)));
        mole.showInfoWindow();

        gran = map.addMarker(new MarkerOptions()
                .position(GRANM)
                .title("Gran Madre Cathedral")
                .snippet("Final Point of Interest")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.symbol)));
        gran.showInfoWindow();

        turin.setVisible(false);
        start.setVisible(false);
        mole.setVisible(false);
        gran.setVisible(false);

      /*  PolylineOptions addPathOptions = new PolylineOptions()
                .add(new LatLng(45.071412, 7.685223))
                .add(new LatLng(45.069025, 7.693235))
                .width(3)
                .color(Color.RED);

        path = map.addPolyline(addPathOptions);
        */

        //path.setVisible(false);
        if (mapPolyLine != null)
        {
            deletePolyLine();
        }
        LatLng origin = new LatLng(45.071412, 7.685223);
        LatLng destination = new LatLng(45.062578, 7.699436);
         getDirectionForCoordinates(origin, destination);
        int getResult = getIntent().getIntExtra("stage", 99);
        System.out.println(getResult);

        if(getResult==1)
            createStage1Markers();
        else if(getResult==2)
            createStage2Markers();
        else if(getResult==3)
            createStage3Markers();

        currentLocation = map.addMarker(new MarkerOptions().position(GRANM));
        this.currentLocation.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(TURIN, 30));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        this.map.setMyLocationEnabled(true);
        this.map.setOnMyLocationChangeListener(this.myLocationChangeListener);
    }
    private void getDirectionForCoordinates(LatLng origin, LatLng destination)
    {
        Direction direction = new Direction();

        //requestPath req = new requestPath();
        //req.execute();
        List<LatLng> returnedPnt = null;
        if (destination != null) {
            returnedPnt = direction.executingRequest(origin, destination, "AIzaSyAs_B5cpevNwMn4k0R0QvbT2d8Yu88SN9s");
            Log.d("Returned Points to Main:", returnedPnt.toString());
            drawPolyLines(returnedPnt);
        }

    }
    private void drawPolyLines(List<LatLng>listLoc)
    {
        if ( map == null )
        {
            return;
        }
        if ( listLoc.size() < 2 )
        {
            return;
        }
        PolylineOptions options = new PolylineOptions();
        options.color( Color.parseColor("#938AF7") );
        options.width( 5 );
        options.visible( true );

        for ( LatLng locRecorded : listLoc )
        {
            options.add( locRecorded );
        }

        mapPolyLine = map.addPolyline( options );

    }
    private void deletePolyLine()
    {

        mapPolyLine.remove();

    }

    private void createStage3Markers() {
        start.setVisible(true);
        turin.setVisible(true);
        mole.setVisible(true);
        gran.setVisible(true);
    }

    private void createStage2Markers() {
        start.setVisible(true);
        turin.setVisible(true);
        mole.setVisible(true);
        //path.setVisible(true);

    }

    private void createStage1Markers() {
        start.setVisible(true);
        turin.setVisible(true);
    }


    GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {

    @Override
    public void onMyLocationChange(Location location) {

        LatLng newLoc = new LatLng(location.getLatitude(), location.getLongitude());
       // currentLocation.setPosition(newLoc);
       // map.animateCamera(CameraUpdateFactory.newLatLngZoom(newLoc, 16.0f));
      //  checkLocation();
        //addPath(start.getPosition(), currentLocation.getPosition());

    }
};

    //private void addPath(LatLng position1, LatLng position2) {

    //}

    private void checkLocation(){
        if (currentLocation.getPosition().latitude==45.069025&&
                currentLocation.getPosition().longitude==7.693235){
            Intent intent = new Intent(context, Challenge3Activity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}