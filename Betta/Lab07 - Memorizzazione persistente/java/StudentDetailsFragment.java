package com.example.lab07;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class StudentDetailsFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("StudentDetailsFragment","onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_details, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d("StudentDetailsFragment","onStart");

        //devo associare il listener ai bottoni
        Button bBack = getActivity().findViewById(R.id.buttonBackFind);
        bBack.setOnClickListener(this);

        //recupero la matricola passata nel bundle
        String matricola = getArguments().getString("matricola");
        Log.d("StudentDetailsFragment","Contenuto Bundle: ".concat(matricola));

        //url per la chiamata ai dettagli
        final String url = "https://ewserver.di.unimi.it/mobicomp/esercizi/getstudent.php";

        //questa volta mi serve passare al server la matricola (id) come parametro
        //questo oggetto verrà inserito nel body della richiesta
        final JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("sid", Integer.parseInt(matricola));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //inizializzo la richiesta: questa volta aggiungo il body
        JsonObjectRequest request = new JsonObjectRequest(
                url,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Volley", "Correct: " + response.toString());
                        //recupero le view che mi servono
                        TextView tvNome = getActivity().findViewById(R.id.textViewNome);
                        TextView tvCognome = getActivity().findViewById(R.id.textViewCognome);
                        TextView tvMatricola = getActivity().findViewById(R.id.textViewMatricola);

                        //imposto il testo nelle textview
                        try {
                            tvNome.setText(response.getString("name"));
                            tvCognome.setText(response.getString("surname"));
                            tvMatricola.setText(response.getString("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        MainActivity.requestQueue.add(request);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBackFind:{
                Log.d("StudentDetailsFragment","buttonBackFind click");

                //passo al fragment start
                Utils.changeFragment(getActivity(), (AppCompatActivity) getActivity(), new StartFragment());
                break;
            }
            default: {
                Log.d("StudentDetailsFragment","Unknown Button ID");
                throw new RuntimeException("Unknown Button ID");
            }
        }
    }
}