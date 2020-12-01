package com.example.lab08es1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

//Quasi tutto il codice che vedi scritto è preso dalla pagina di mapbox consigliata dal prof durante la lezione
//ricordati di seguire anche tutti i passaggi di setup di mapbox (sempre spiegati molto accuratamente in documentazione)

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MapView mapView;
    private MapboxMap mapboxMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main Activity", "onCreate");

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_main);

        Button bSetPosition = findViewById(R.id.buttonSetPosition);
        bSetPosition.setOnClickListener(this);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                Log.d("Main Activity", "Map ready");

                //devo salvare l'oggetto mapboxMap perchè mi serve, ad esempio, per cambiare la posizione della camera
                MainActivity.this.mapboxMap = mapboxMap;

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        Log.d("Main Activity", "Map style loaded");
                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments

                        //imposto la posizione della camera dove voglio io
                        CameraPosition position = new CameraPosition.Builder()
                                .target(new LatLng(51.50550, -0.07520))
                                .zoom(21)
                                .tilt(20)
                                .build();
                        mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
        Log.d("Main Activity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        Log.d("Main Activity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        Log.d("Main Activity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
        Log.d("Main Activity", "onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
        Log.d("Main Activity", "onSaveInstanceState");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
        Log.d("Main Activity", "onLowMemory");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        Log.d("Main Activity", "onDestroy");
    }

    @Override
    public void onClick(View v) {
        Log.d("Main Activity", "onClick");
        //devo assicurarmi che l'oggetto sia pronto
        if (mapboxMap!=null) {
            //al click del pulsante, cambio la posizione della camera
            EditText etLat = findViewById(R.id.editTextLat);
            EditText etLon = findViewById(R.id.editTextLon);
            Double lat;
            Double lon;
            try {
                lat = Double.valueOf(etLat.getText().toString());
                lon = Double.valueOf(etLon.getText().toString());
                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(lat, lon))
                        .zoom(21)
                        .tilt(20)
                        .build();
                mapboxMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(position), 7000);
            }catch (Exception e){
                Log.d("Main Activity", e.getMessage());
                Log.d("Main Activity, on click","Getting doubles from text");
                Toast.makeText(this,"Valori di latitudine e longitudine non validi", Toast.LENGTH_LONG);
            }
            Log.d("Main Activity", "Changing map position");

        }
    }
}