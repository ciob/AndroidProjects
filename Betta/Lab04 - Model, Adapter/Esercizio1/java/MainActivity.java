package com.example.lab04es1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

// Questa soluzione utilizza i fragment
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main Activity", "onCreate");

        //imposto la schermata inziale, che in questo caso è il fragment StartFragment
        StartFragment start = new StartFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, start).commit();
        Log.d("Main Activity", "switching to StartFragment");

    }
}