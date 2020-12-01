package com.example.lab04es2;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView myTextView;

    public ViewHolder(View itemView) {
        super(itemView);
        Log.d("ViewHolder", "Constructor");
        myTextView = itemView.findViewById(R.id.textViewSingleRow);
    }

    public void setText(String text) {
        Log.d("ViewHolder", "setText");
        myTextView.setText(text);
    }

}
