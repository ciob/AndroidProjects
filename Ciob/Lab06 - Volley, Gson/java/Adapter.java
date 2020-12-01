package com.example.lab06bis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{

    private LayoutInflater mInflater;
    private OnRecyclerViewClickListener mRecyclerViewClickListener;

    public Adapter(Context context, OnRecyclerViewClickListener recyclerViewClickListener){
        this.mInflater = LayoutInflater.from(context);
        this.mRecyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.content_holder, parent, false);
        return new ViewHolder(view,mRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = StudentModel.getInstance().getStudent(position);
        holder.updateContent(student);
    }

    @Override
    public int getItemCount() {
        return StudentModel.getInstance().getSize();
    }
}
