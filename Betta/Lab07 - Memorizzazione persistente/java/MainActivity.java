package com.example.lab07;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //instanzio una volta la coda delle richieste per non doverne dichiarare di più
    public static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main Activity", "onCreate");
        final Activity thisActivity = this;
        requestQueue = Volley.newRequestQueue(this);

        if(Utils.checkFirstAccess(this)){
            Log.d("Main Activity", "FIRST ACCESS");
            final String url = "https://ewserver.di.unimi.it/mobicomp/esercizi/getstudents.php";
            //ecco la richiesta: imposto il tipo (get), l'url e a null il body della richiesta
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d("Volley", "Correct: " + response.toString());
                            try {
                                //trasformo il json object nell'array di studenti che mi serve
                                final ArrayList<Student> arrStudent = Utils.getArrayStudentFromJsonObject(response);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            //voglio un array di studenti perchè il mio metodo del DAO funziona con gli array (non ArrayList)
                                            ApplicationModel.getInstance(getApplicationContext()).addStudents(arrStudent.toArray(new Student[0]));
                                            // dato che la lettura dei dati da rete è andata a buon fine, imposto la variabile di primo accesso a false
                                            Utils.setFirstAccess(thisActivity);
                                            //passo al fragment start
                                            Utils.changeFragment(thisActivity, (AppCompatActivity) thisActivity,new StartFragment());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                            } catch (Exception e) {
                                //questo catch invece è per l'eccezione che solleva il mio ApplicationModel
                                e.printStackTrace();
                                Log.d("ApplicationModel",e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Volley", "Error: " + error.toString());
                }
            });
            Log.d("Volley", "Sending request");
            //incodo la richiesta
            requestQueue.add(request);
        }
        else{
            Log.d("Main Activity", "NOT first access");
            //leggo dal database
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.d("Main Activity", "reading students from db");
                        //aggiorno i dati della lista con quelli presenti nel database
                        ApplicationModel.getInstance(getApplicationContext()).getStudentsFromDB();
                        //imposto la schermata inziale, che in questo caso è il fragment StartFragment
                        Utils.changeFragment(thisActivity,(AppCompatActivity) thisActivity, new StartFragment());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}