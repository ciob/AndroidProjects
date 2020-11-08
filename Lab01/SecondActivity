package com.example.lab01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","Fino a qui ci arrivo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent receivedIntent = getIntent();

        if (receivedIntent.hasExtra("textToPass")) {
            String value = receivedIntent.getStringExtra("textToPass");

            TextView textView = findViewById(R.id.textReceived);
            textView.setText(value);
        }
    }

    public void openDialer(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:z"));
        startActivity(intent);
    }
}
