package com.example.lab04es4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

        //Questa parte di codice è in un try/catch perché il metodo getStudentByMatricola()
        //del model che ho creato solleva eccezione quando non trova la matricola
        try {
            //cerco lo studente che ha come matricola quella passata nel bundle
            Student s = ApplicationModel.getInstance().getStudentByMatricola(matricola);
            Log.d("StudentDetailsFragment","Studente trovato");

            //recupero le view che mi servono
            TextView tvNome = getActivity().findViewById(R.id.textViewNome);
            TextView tvCognome = getActivity().findViewById(R.id.textViewCognome);
            TextView tvMatricola = getActivity().findViewById(R.id.textViewMatricola);

            //imposto il testo nelle textview
            tvNome.setText(s.getNome());
            tvCognome.setText(s.getCognome());
            tvMatricola.setText(s.getMatricola());

        } catch (Exception e) {
            Log.d("StudentDetailsFragment", "Matricola studente non trovata");
            Toast.makeText(getActivity(), "Matricola non trovata", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBackFind:{
                Log.d("StudentDetailsFragment","buttonBackFind click");

                //passo al fragment start
                StartFragment start = new StartFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,start);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Log.d("StudentDetailsFragment", "switching to startFragment");
                break;
            }
            default: {
                Log.d("StudentDetailsFragment","Unknown Button ID");
                throw new RuntimeException("Unknown Button ID");
            }
        }
    }
}