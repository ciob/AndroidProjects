# AndroidProjects
Raccolta progetti Android.  
Ogni Lab contiene cartelle `java` e `layout` replicabili.  
  
## Lab01 - Activity/Intent 

`MainActivity` Informa tramite log quando partono le varie fasi del ciclo di vita dell'app. Inoltre definisce cosa succede alla pressione del button: creaimo l'intent per passare ad una nuova activity, passandole anche una stringa.  
`SecondActivity` Riceve la stringa, la mette nella casella di testo e, tramite una funzione simile a quella del pulsante del MainActivity, apre l'app Telefono.

## Lab06 - Volley/Gson 

`Volley` Libreria per gestione richieste HTTP  
`Gson` Libreria per (de)serializzazione JSON  

Prima di iniziare     

Nel file `AndroidManifest.xml` permesso per utilizzare Internet  
`<uses-permission android:name="android.permission.INTERNET" />`  

Nel file `Build.gradle.app` definire dipendeze   
`implementation 'com.android.volley:volley:1.1.1'`  
`implementation 'com.google.code.gson:gson:2.8.6'`  

## Lab07 - Position


