package com.example.lab04es4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private LayoutInflater mInflater;
    private ListStudentsFragment listFragment;

    //passo il riferimento dell'activity, che in questo caso è il fragment ListStudentsFragment
    public Adapter(ListStudentsFragment fragment) {
        this.mInflater = LayoutInflater.from(fragment.getContext());
        this.listFragment = fragment;
    }

    // viene richiamato quando si crea un nuovo oggetto di view (che rappresenta una cella)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_row, parent, false);
        return new ViewHolder(view, listFragment);
    }

    // viene richiamato quando un oggetto di view (che rappresenta una cella) viene associato ai suoi dati (nota che un
    //parametro del metodo è la posizione dell’oggetto nella lista)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student s = ApplicationModel.getInstance().getStudentByIndex(position);
        holder.setText(s);
    }

    //viene chiamato per sapere quanti elementi ci sono nella lista (model) da mostrare
    @Override
    public int getItemCount() {
        return ApplicationModel.getInstance().getSize();
    }
}
