package com.example.lab06es2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    TextView tvPermission;
    //questa costante intera mi serve a capire quale permesso voglio ottenere
    private static final int MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 0;

    //per ultima posizione nota
    private FusedLocationProviderClient fusedLocationClient;

    //per aggiornamenti di posizione
    private LocationCallback locationCallback;
    private boolean requestingLocationUpdates = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //callback per gli aggiornamenti di posizione
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                //aggiorno i dati nelle textView con le informazioni della posizione aggiornata
                TextView tvUpdatedLocationLongitude = findViewById(R.id.textViewUpdatedLocationLongitude);
                TextView tvUpdatedLocationLatitude = findViewById(R.id.textViewUpdatedLocationLatitude);
                TextView tvUpdatedLocationAccuracy = findViewById(R.id.textViewUpdatedLocationAccuracy);
                TextView tvUpdatedLocationHour = findViewById(R.id.textViewUpdatedLocationHour);
                for (Location location : locationResult.getLocations()) {
                    Log.d("Location", "New Location received: " + location.toString());
                    tvUpdatedLocationLongitude.setText("Longitude: ".concat(String.valueOf(location.getLongitude())));
                    tvUpdatedLocationLatitude.setText("Latitude: ".concat(String.valueOf(location.getLatitude())));
                    tvUpdatedLocationAccuracy.setText("Accuracy: ".concat(String.valueOf(location.getAccuracy())));
                    tvUpdatedLocationHour.setText("Time: ".concat(String.valueOf(location.getTime())));
                }
            };
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
        tvPermission = findViewById(R.id.textViewPermissionResult);
        //controllo i permessi di posizione
        if(checkLocationPermission()){
            tvPermission.setText("Permessi OK");
            Log.d("MainActivity", "Permessi OK");
            //richiedo l'ultima posizione
            getLastKnownLocation();
            //richiedo aggiornamenti sulla posizione
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //se non voglio ottenere aggiornamenti di posizione in background, mi devo ricordare di sospenderli
        fusedLocationClient.removeLocationUpdates(locationCallback);
        requestingLocationUpdates = false;
        TextView tvUpdatedLocation = findViewById(R.id.textViewUpdatedLocation);
        tvUpdatedLocation.setText("Request suspended");
    }


    //metodo per verificare i permessi sulla posizione
    public boolean checkLocationPermission(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //ho i permessi, scrivo nella casella di testo che va tutto bene
            Log.d("Location", "The permission is granted");
            tvPermission.setText("Permessi OK");
            return true;
        }
        else{
            //non ho i permessi, devo richiederli
            Log.d("Location", "Ask for permission");
            tvPermission.setText("Permessi da chiedere");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
            return false;
        }
    }


    //gestiamo la risposta dell'utente
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    Log.d("Location", "Now the permission is granted");
                    tvPermission.setText("Permessi OK");

                    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                    getLastKnownLocation();
                } else {
                    // permission was not granted
                    Log.d("Location", "Permission still not granted");
                    tvPermission.setText("Permessi non dati");
                }
                break;
            }
        }
    }

    //per chiedere l'ultima posizione nota
    public void getLastKnownLocation(){
        if (checkLocationPermission()) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            TextView tvLastKnownLocation = findViewById(R.id.textViewLastKnownLocation);
                            TextView tvLastKnownLocationLongitude = findViewById(R.id.textViewLastKnownLocationLongitude);
                            TextView tvLastKnownLocationLatitude = findViewById(R.id.textViewLastKnownLocationLatitude);
                            TextView tvLastKnownLocationAccuracy = findViewById(R.id.textViewLastKnownLocationAccuracy);
                            if (location != null) {
                                Log.d("Location", "Last known location:" + location.toString());
                                tvLastKnownLocation.setText("Last Known location");
                                tvLastKnownLocationLongitude.setText("Longitude: ".concat(String.valueOf(location.getLongitude())));
                                tvLastKnownLocationLatitude.setText("Longitude: ".concat(String.valueOf(location.getLatitude())));
                                tvLastKnownLocationAccuracy.setText("Longitude: ".concat(String.valueOf(location.getAccuracy())));
                            } else {
                                Log.d("Location", "Last Known location not available");
                                tvLastKnownLocation.setText("Last Known location not available");
                            }
                        }
                    });
        }
    }

    //per chiedere la posizione aggiornata
    private void startLocationUpdates() {
        if(checkLocationPermission()) {
            TextView tvUpdated = findViewById(R.id.textViewUpdatedLocation);
            tvUpdated.setText("Updated Location");

            //inizio a chiedere gli aggiornamenti di posizione
            requestingLocationUpdates = true;
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setInterval(1000); //in ms.
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            fusedLocationClient.requestLocationUpdates(locationRequest,locationCallback,Looper.getMainLooper());
        }
    }
    
}