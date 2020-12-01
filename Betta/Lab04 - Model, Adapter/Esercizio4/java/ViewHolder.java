package com.example.lab04es4;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView tvNome;
    private TextView tvCognome;
    private TextView tvMatricola;
    private Button buttonDel;
    private ListStudentsFragment listFragment;

    //passo il riferimento dell'activity, che in questo caso è il fragment ListStudentsFragment
    public ViewHolder(View itemView, ListStudentsFragment fragment) {
        super(itemView);

        //imposto il riferimento
        this.listFragment = fragment;

        //imposto il listener sull'elemento della lista
        itemView.setOnClickListener(this);


        //recupero altri elementi di view che mi serviranno
        tvNome = itemView.findViewById(R.id.textViewSingleRowNome);
        tvCognome = itemView.findViewById(R.id.textViewSingleRowCognome);
        tvMatricola = itemView.findViewById(R.id.textViewSingleRowMatricola);
        buttonDel  = itemView.findViewById(R.id.buttonSingleRowDelete);

        //imposto il listener sul button
        buttonDel.setOnClickListener(this);
    }

    public void setText(Student s) {
        Log.d("ViewHolder", "setText");
        tvNome.setText(s.getNome());
        tvCognome.setText(s.getCognome());
        tvMatricola.setText(s.getMatricola());
    }

    @Override
    public void onClick(View v) {
        Log.d("ViewHolder", "OnClick on Element: " + tvMatricola.getText().toString() + " with position: " + getAdapterPosition());        //richiamo il metodo nel fragment della lista
        //il fragmement di riferimento gestirà l'evento
        listFragment.onClickViewHolder(v, getAdapterPosition());
    }
}
