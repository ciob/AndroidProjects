package com.example.lab05;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class StartFragment extends Fragment {

    private Adapter adapter ;

    @Override
    public void onStart() {
        super.onStart();
        Log.d("StartFragment","onStart");
        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerView);

        //definiamo il layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //creiamo l'adapter e lo associamo alla recyclerView
        adapter = new Adapter( this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("StartFragment","onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }


    public void onClickViewHolder(View view, int position) {
        Log.d("StartFragment", "Tap event on item: " + position);

        //mostro i dettagli dello studente selezionato passando nel bundle la matricola
        StudentDetailsFragment fragment = new StudentDetailsFragment();
        Log.d("StartFragment", "Tap event on matricola: "+ ApplicationModel.getInstance().getStudents().get(position).getMatricola());
        Bundle args = new Bundle();
        args.putString("matricola", ApplicationModel.getInstance().getStudents().get(position).getMatricola());
        fragment.setArguments(args);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}