package com.example.lab06bis;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private TextView nomeHolder;
    private TextView congomeHolder;
    private TextView matricolaHolder;
    private OnRecyclerViewClickListener mRecyclerViewClickListener;

    public ViewHolder(@NonNull View itemView,OnRecyclerViewClickListener recyclerViewClickListener) {
        super(itemView);
        nomeHolder = itemView.findViewById(R.id.nomeHolder);
        congomeHolder = itemView.findViewById(R.id.cognomeHolder);
        matricolaHolder = itemView.findViewById(R.id.matricolaHolder);

        itemView.setOnClickListener(this);
        this.mRecyclerViewClickListener = recyclerViewClickListener;
    }

    public void updateContent(Student s){
        nomeHolder.setText(s.getName());
        congomeHolder.setText(s.getSurname());
        matricolaHolder.setText(s.getId());
    }

    @Override
    public void onClick(View v) {
        Log.d("ciob","cliccato Holder"+getAdapterPosition());
        mRecyclerViewClickListener.OnRecyclerViewClick(v,getAdapterPosition());
    }
}
