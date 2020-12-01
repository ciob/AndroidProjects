package com.example.lab03es1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//VARIANTE 2: è proprio la mia main activity ad essere la
// classe che gestisce l'evento di click
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //questi booleani servono per scegliere la variante che
    //deve eseguire il codice
    public boolean var1 = true;
    public boolean var2 = false;
    public boolean var3 = false;
    public boolean var4 = false;
    public boolean var5 = false;
    //dichiaro la text view una volta per tutte
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ottengo la text view
        myTextView = findViewById(R.id.pippo);
        //resetto il numero nella casella di testo
        myTextView.setText(String.valueOf(0));

        //ottengo i riferimenti ai button
        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);

        //VARIANTE 1
        if (var1) {
            Log.d("MainActivity", "Variante 1 attiva");
            //instanzio la mia classe che gestisce l'evento
            MyClickListener myClickListener = new MyClickListener(myTextView);

            //aggancio i bottoni all'istanza della classe che gestisce l'evento
            b1.setOnClickListener(myClickListener);
            b2.setOnClickListener(myClickListener);
            b3.setOnClickListener(myClickListener);
            b4.setOnClickListener(myClickListener);
        }

        //VARIANTE 2
        if (var2){
            Log.d("MainActivity", "Variante 2 attiva");
            //aggancio i bottoni all'istanza della classe che gestisce l'evento
            // (in questo caso è la main activity)
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);
        }

        //VARIANTE 3
        if (var3){
            Log.d("MainActivity", "Variante 3 attiva");
            //aggancio i bottoni all'istanza della classe che gestisce l'evento
            // (in questo caso è la main activity)
            b1.setOnClickListener(myButtonClickListener);
            b2.setOnClickListener(myButtonClickListener);
            b3.setOnClickListener(myButtonClickListener);
            b4.setOnClickListener(myButtonClickListener);
        }

        //VARIANTE 4
        if(var4){
            Log.d("MainActivity", "Variante 4 attiva");

            //devo avere una text view dichiarata final
            final TextView otherMyTextView = findViewById(R.id.pippo);

            //aggancio il listener direttamente a ciascun button
            b1.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d("MainActivity", "b1 clicked, variante 4");
                    //ottengo il valore attuale della text view (da dichiarare final)
                    int n = Integer.parseInt((String) otherMyTextView.getText());
                    n++;
                    //infine cambio il contenuto della mia text view
                    otherMyTextView.setText(String.valueOf(n));
                }
            });

            b2.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d("MainActivity", "b2 clicked, variante 4");
                    //ottengo il valore attuale della text view (da dichiarare final)
                    int n = Integer.parseInt((String) otherMyTextView.getText());
                    n--;
                    //infine cambio il contenuto della mia text view
                    otherMyTextView.setText(String.valueOf(n));
                }
            });

            b3.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d("MainActivity", "b3 clicked, variante 4");
                    //ottengo il valore attuale della text view (da dichiarare final)
                    int n = Integer.parseInt((String) otherMyTextView.getText());
                    n=n*2;
                    //infine cambio il contenuto della mia text view
                    otherMyTextView.setText(String.valueOf(n));
                }
            });

            b4.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d("MainActivity", "b4 clicked, variante 4");
                    //ottengo il valore attuale della text view (da dichiarare final)
                    int n = Integer.parseInt((String) otherMyTextView.getText());
                    n=n*n;
                    //infine cambio il contenuto della mia text view
                    otherMyTextView.setText(String.valueOf(n));
                }
            });
        }
        //VARIANTE 5
        //attenzione: supportata da java 8 in poi
        if (var5){
            b1.setOnClickListener(e -> {
                Log.d("MainActivity", "b1 clicked, variante 5");
                int n = Integer.parseInt((String) myTextView.getText());
                n++;
                myTextView.setText(String.valueOf(n));
            });
            b2.setOnClickListener(e -> {
                Log.d("MainActivity", "b2 clicked, variante 5");
                int n = Integer.parseInt((String) myTextView.getText());
                n--;
                myTextView.setText(String.valueOf(n));
            });
            b3.setOnClickListener(e -> {
                Log.d("MainActivity", "b3 clicked, variante 5");
                int n = Integer.parseInt((String) myTextView.getText());
                n=n*2;
                myTextView.setText(String.valueOf(n));
            });
            b4.setOnClickListener(e -> {
                Log.d("MainActivity", "b4 clicked, variante 5");
                int n = Integer.parseInt((String) myTextView.getText());
                n=n*n;
                myTextView.setText(String.valueOf(n));
            });
        }
    }

    //VARIANTE 2
    //gestisco l'evento di click con il metodo onClick
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
                Log.d("MainActivity", "b1 clicked, variante 2");
                n++;
                break;
            }
            case R.id.button2: {
                Log.d("MainActivity", "b2 clicked, variante 2");
                n--;
                break;
            }
            case R.id.button3: {
                Log.d("MainActivity", "b3 clicked, variante 2");
                n = n*2;
                break;
            }
            case R.id.button4: {
                Log.d("MainActivity", "b4 clicked, variante 2");
                n = n * n;
                break;
            }
            default:
                //se il metodo viene chiamato da una view sconosciuta,
                //sollevo un'eccezione
                throw new RuntimeException("Unknown button id!!");
        }
        //infine cambio il contenuto della mia text view
        myTextView.setText(String.valueOf(n));
    }

    //VARIANTE 3
    //costruisco la classe anonima
    private View.OnClickListener myButtonClickListener = new View.OnClickListener() {
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
                    Log.d("MainActivity", "b1 clicked, variante 3");
                    n++;
                    break;
                }
                case R.id.button2: {
                    Log.d("MainActivity", "b2 clicked, variante 3");
                    n--;
                    break;
                }
                case R.id.button3: {
                    Log.d("MainActivity", "b3 clicked, variante 3");
                    n = n*2;
                    break;
                }
                case R.id.button4: {
                    Log.d("MainActivity", "b4 clicked, variante 4");
                    n = n * n;
                    break;
                }
                default:
                    //se il metodo viene chiamato da una view sconosciuta,
                    //sollevo un'eccezione
                    Log.d("MainActivity", "Unknown button id");
                    throw new RuntimeException("Unknown button id!!");
            }
            //infine cambio il contenuto della mia text view
            myTextView.setText(String.valueOf(n));
        }
    };
}