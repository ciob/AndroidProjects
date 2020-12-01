package com.example.lab04es4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class StartFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("StartFragment","onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("StartFragment","onStart");

        //devo associare il listener ai bottoni
        Button bFind = getActivity().findViewById(R.id.buttonFind);
        bFind.setOnClickListener(this);
        Button bAdd = getActivity().findViewById(R.id.buttonAdd);
        bAdd.setOnClickListener(this);
        Button bList = getActivity().findViewById(R.id.buttonList);
        bList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAdd:{
                Log.d("StartFragment","onClick: buttonAdd");

                //passo al fragment che aggiunge gli studenti
                AddStudentFragment addS = new AddStudentFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,addS);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Log.d("StartFragment", "switching to AddStudentFragment");
                break;
            }
            case R.id.buttonFind:{
                Log.d("StartFragment","onClick: buttonFind");

                //recupero il riferimento alla casella di testo
                EditText etFind = getActivity().findViewById(R.id.editTextStart);
                String matricola = String.valueOf(etFind.getText());
                Log.d("StartFragment","Voglio cercare: ".concat(matricola));

                //preparo la transazione aggiungendo al bundle la matricola
                StudentDetailsFragment studentDetailsFragment = new StudentDetailsFragment();
                Bundle args = new Bundle();
                args.putString("matricola", matricola);
                studentDetailsFragment.setArguments(args);
                Log.d("StartFragment","Contenuto Bundle: ".concat(args.getString("matricola")));

                //passo al fragment che visualizza i dettagli
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,studentDetailsFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Log.d("StartFragment", "switching to StudentDetailsFragment");
                break;
            }
            case R.id.buttonList: {
                Log.d("startFragment","buttonListClick");

                //passo al fragment che visualizza la lista
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new ListStudentsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Log.d("startFragment", "switching to ListStudentsFragment");
                break;
            }
            default: {
                Log.d("StartFragment","Unknown Button ID");
                throw new RuntimeException("Unknown Button ID");
            }
        }
    }
}