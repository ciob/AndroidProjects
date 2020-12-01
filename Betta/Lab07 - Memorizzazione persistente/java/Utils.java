package com.example.lab07;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Utils {
    //metodo per controllare il primo accesso all'applicazione
    public static boolean checkFirstAccess(Activity activity){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        Boolean first_access = sharedPreferences.getBoolean("first_access",true);
        return first_access;
    }

    //metodo per impostare il valore di first access
    public static void setFirstAccess(Activity activity){
        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean("first_access", false);
        editor.commit();
        return;
    }

    //metodo per ottenere l'arraylist dal risultato della chiamata di rete
    public static ArrayList<Student> getArrayStudentFromJsonObject(JSONObject jsonObject){
        try {
            JSONArray arrayStudent = jsonObject.getJSONArray("students");
            final ArrayList<Student> tempArray = new ArrayList<>();
            for(int i=0; i<arrayStudent.length(); i++){
                JSONObject obj = arrayStudent.getJSONObject(i);
                tempArray.add(new Student(obj.getString("name"), obj.getString("surname"),obj.getString("id")));
            }
            return tempArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    //metodo per cambiare fragment
    public static void changeFragment(Activity activity,final AppCompatActivity appCompatActivityactivity, final Fragment fragment){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = appCompatActivityactivity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Log.d("Utils", "switching fragments");
            }
        });
    }

    //metodo per cambiare fragment
    public static void changeFragment(Activity activity,final AppCompatActivity appCompatActivityactivity, final Fragment fragment, Bundle bundle){
        fragment.setArguments(bundle);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = appCompatActivityactivity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Log.d("Utils", "switching fragments");
            }
        });
    }
}
