package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //instanzio una volta la coda delle richieste per non doverne dichiarare di più
    public static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main Activity", "onCreate");

        //inizializzo la coda delle richieste
        requestQueue= Volley.newRequestQueue(this);

        //la parte che segue può essere implementata anche nel fragment

        //url della richiesta di tutti gli studenti
        final String url = "https://ewserver.di.unimi.it/mobicomp/esercizi/getstudents.php";

        //ecco la richiesta: imposto il tipo (get), l'url e a null il body della richiesta
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley", "Correct: " + response.toString());
                        try {
                            //ottengo l'array di studenti
                            JSONArray arrayStudent = response.getJSONArray("students");
                            //inserisco ogni studente nell'array del model
                            for(int i=0; i<arrayStudent.length(); i++){
                                JSONObject obj = arrayStudent.getJSONObject(i);
                                ApplicationModel.getInstance().addStudent(new Student(obj.getString("name"), obj.getString("surname"),obj.getString("id")));
                                Log.d("MainActivity",ApplicationModel.getInstance().getStudents().get(i).getNome());
                            }

                            //imposto la schermata inziale, che in questo caso è il fragment StartFragment
                            StartFragment start = new StartFragment();
                            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, start).commit();
                            Log.d("Main Activity", "switching to StartFragment");
                        }
                        catch (JSONException e) {
                            //vedrai molti try/catch: ogni volta che uso i JSONObject devo
                            //gestire le possibili eccezioni che questa classe solleva
                            e.printStackTrace();
                            Log.d("Volley",e.getMessage());
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
}