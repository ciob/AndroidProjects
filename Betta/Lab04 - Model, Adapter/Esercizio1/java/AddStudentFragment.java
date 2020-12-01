package com.example.lab04es1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentFragment extends Fragment implements View.OnClickListener{

    ApplicationModel applicationModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("addStudentFragment","onCreateView");

        //recupero il model
        applicationModel = ApplicationModel.getInstance();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("addStudentFragment","onStart");

        //devo associare il listener ai bottoni
        Button bAdd = getActivity().findViewById(R.id.buttonAddStudent);
        bAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAddStudent:{
                Log.d("addStudentFragment","buttonAddStudentClick");
                //recupero le view che mi servono
                EditText etNome = getActivity().findViewById(R.id.editTextNome);
                EditText etCognome = getActivity().findViewById(R.id.editTextCognome);
                EditText etMatricola = getActivity().findViewById(R.id.editTextMatricola);

                //leggo i valori nei campi di testo
                String nome = String.valueOf(etNome.getText());
                String cognome = String.valueOf(etCognome.getText());
                String matricola = String.valueOf(etMatricola.getText());

                //Creo lo studente
                Student s = new Student(nome, cognome, matricola);

                //Questa parte di codice è in un try/catch perché il metodo getStudentByMatricola()
                //del model che ho creato solleva eccezione quando non la matricola esiste già
                try {
                    //aggiungo lo studente
                    applicationModel.addStudent(s);
                    Log.d("addStudentFragment","studente aggiunto: "+s.getNome()+" "+s.getCognome()+" "+s.getMatricola());
                } catch (Exception e) {
                    Log.d("addStudentFragment","studente non aggiunto: matricola già esistente");
                    Toast.makeText(getActivity(), "Matricola già esistente", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                //passo al fragment start
                StartFragment start = new StartFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,start);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Log.d("addStudentFragment", "switching to startFragment");
                break;
            }
            default: {
                Log.d("addStudentFragment","Unknown Button ID");
                throw new RuntimeException("Unknown Button ID");
            }
        }
    }
}