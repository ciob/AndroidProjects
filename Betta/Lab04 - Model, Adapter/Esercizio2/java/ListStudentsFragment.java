package com.example.lab04es2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListStudentsFragment extends Fragment implements View.OnClickListener {

    private Adapter adapter ;

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ListStudentsFragment","onStart");
        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerView);

        //definiamo il layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //creiamo l'adapter e lo associamo alla recyclerView
        adapter = new Adapter( this);
        recyclerView.setAdapter(adapter);

        //devo associare il listener ai bottoni
        Button bHome = getActivity().findViewById(R.id.buttonBackList);
        bHome.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ListStudentsFragment","onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_students, container, false);
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBackList: {
                Log.d("ListStudentsFragment","buttonBackList click");

                //passo al fragment start
                StartFragment start = new StartFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,start);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Log.d("ListStudentsFragment", "switching to startFragment");
                break;
            }
        }
    }
}