package com.example.lab06es1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //questa costante intera mi serve a capire quale permesso voglio ottenere
    private static final int MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");
        //controllo i permessi di posizione
        checkLocationPermission();
    }


    //metodo per verificare i permessi sulla posizione
    public boolean checkLocationPermission(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //ho i permessi, scrivo nella casella di testo che va tutto bene
            setTextView(true);
            Log.d("Location", "The permission is granted");
            return true;
        }
        else{
            //non ho i permessi, devo richiederli
            Log.d("Location", "Ask for permission");
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
                    setTextView(true);
                } else {
                    // permission was not granted
                    Log.d("Location", "Permission still not granted");
                    setTextView(false);
                }
                break;
            }
        }
    }

    public void setTextView(Boolean permission){
        TextView tvPermission = findViewById(R.id.textViewPermissionResult);
        if(permission){
            tvPermission.setText("Tutto OK, permessi localizzazione dati");
        }
        else{
            tvPermission.setText("E' necessario fornire i permessi di posizione: riavviare l'app e concederli");
        }
    }
    
}