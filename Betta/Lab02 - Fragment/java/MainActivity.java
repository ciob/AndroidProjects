package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String BUNDLE_KEY_TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirstFragment firstFragment = new FirstFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
    }

    public void onButtonTapped(View v) {
        Log.d("MyMainActivity", "button tapped in the fragment");
        TextView tv = findViewById(R.id.editText);
        String text = tv.getText().toString();
        Log.d("MyMainActivity", "Text: " + text);


        SecondFragment newFragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_TEXT, text);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        Log.d("MyMainActivity", "transaction committed");
    }
}
