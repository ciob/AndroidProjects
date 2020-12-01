package com.example.lab03es1;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
//VARIANTE 1
public class MyClickListener implements View.OnClickListener {

    //memorizzo la text view in modo tale da poterne
    // richiamare il metodo per modificare il testo
    private TextView myTextView;

    //ogni istanza di questa classe avrà associata un'unica text view
    //costruttore
    public MyClickListener(TextView tv){
        myTextView = tv;
    }

    //quando clicco sul button cambio il testo della textview
    //con questo codice gestisco tutti i button con un'unica classe
    @Override
    public void onClick(View v) {
        //ottengo il valore attuale della text view
        int n = Integer.parseInt((String) myTextView.getText());

        //devo capire quale button ha generato l'evento:
        //lo faccio distinguendo gli id dei button che ho
        //ottengo gli id attraverso la view (che sarà un button)
        // passata per riferimento nei parametri di questo metodo
        switch(v.getId()){
            case R.id.button1: {
                Log.d("MainActivity", "bt1 clicked, variante 1");
                n++;
                break;
            }
            case R.id.button2: {
                Log.d("MainActivity", "bt2 clicked, variante 1");
                n--;
                break;
            }
            case R.id.button3: {
                Log.d("MainActivity", "bt3 clicked, variante 1");
                n = n*2;
                break;
            }
            case R.id.button4: {
                Log.d("MainActivity", "bt4 clicked, variante 1");
                n = n * n;
                break;
            }
            default:
                //se il metodo viene chiamato da una view sconosciuta,
                //sollevo un'eccezione
                Log.d("MainActivity", "Unknown button id!!");
                throw new RuntimeException("Unknown button id!!");
        }
        //infine cambio il contenuto della mia text view
        myTextView.setText(String.valueOf(n));
    }
}
