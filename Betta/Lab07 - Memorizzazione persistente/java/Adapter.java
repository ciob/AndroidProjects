package com.example.lab07;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private LayoutInflater mInflater;
    private StartFragment startFragment;

    //passo il riferimento dell'activity, che in questo caso è il fragment StartFragment
    public Adapter(StartFragment fragment) {
        this.mInflater = LayoutInflater.from(fragment.getContext());
        this.startFragment = fragment;
    }

    // viene richiamato quando si crea un nuovo oggetto di view (che rappresenta una cella)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_row, parent, false);
        return new ViewHolder(view, startFragment);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student s = ApplicationModel.getInstance().getStudentByIndex(position);
        holder.setText(s);
    }

    @Override
    public int getItemCount() {
        return ApplicationModel.getInstance().getSize();
    }
}
