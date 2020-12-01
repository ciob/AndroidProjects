package com.example.lab04es3;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView tvNome;
    private TextView tvCognome;
    private TextView tvMatricola;


    public ViewHolder(View itemView) {
        super(itemView);
        Log.d("ViewHolder", "Constructor");
        tvNome = itemView.findViewById(R.id.textViewSingleRowNome);
        tvCognome = itemView.findViewById(R.id.textViewSingleRowCognome);
        tvMatricola = itemView.findViewById(R.id.textViewSingleRowMatricola);
    }

    public void setText(Student s) {
        Log.d("ViewHolder", "setText");
        tvNome.setText(s.getNome());
        tvCognome.setText(s.getCognome());
        tvMatricola.setText(s.getMatricola());
    }

}
