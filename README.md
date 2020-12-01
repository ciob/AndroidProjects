# AndroidProjects
Raccolta progetti Android.  
Ogni Lab contiene cartelle `java` e `layout` replicabili.  

## Betta

### Lab01 - Activity/Intent

`MainActivity` Informa tramite log quando partono le varie fasi del ciclo di vita dell'app. Inoltre definisce cosa succede alla pressione del button: creaimo l'intent per passare ad una nuova activity, passandole anche una stringa.  
`SecondActivity` Riceve la stringa, la mette nella casella di testo e, tramite una funzione simile a quella del pulsante del MainActivity, apre l'app Telefono.  

### Lab02 - Fragment

### Lab03 - Eventi

Manca esercizio 2.  

### Lab04 - Model/Adapter

### Lab05 - Thread/Comunicazione

Nel file `AndroidManifest.xml` permesso per utilizzare Internet:  
`<uses-permission android:name="android.permission.INTERNET" />`  

### Lab06 - Location

Prima di iniziare  

Nel file `AndroidManifest.xml` permesso per utilizzare posizione:  
`<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>`  

Nel file `Build.gradle.app` definire dipendenze:  
`implementation 'com.google.android.gms:play-services-location:17.1.0'`  

### Lab07 - Memorizzazioen persistente

Prima di iniziare  

Nel file `AndroidManifest.xml` permesso per utilizzare Internet:  
`<uses-permission android:name="android.permission.INTERNET" />`  

### Lab08 - MapBox

Vedi guida su come settare MapBox  

Prima di iniziare  

Nel file `AndroidManifest.xml` permesso per utilizzare posizione e Internet  
`<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />`  
`<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />`  
`<uses-permission android:name="android.permission.INTERNET" />`  

Modifica Gralde opportunamente per usare MapBox.  

## Ciob

### Lab06 - Volley/Gson

`Volley` Libreria per gestione richieste HTTP  
`Gson` Libreria per (de)serializzazione JSON  

Prima di iniziare  

Nel file `AndroidManifest.xml` permesso per utilizzare Internet  
`<uses-permission android:name="android.permission.INTERNET" />`  

Nel file `Build.gradle.app` definire dipendenze:  
`implementation 'com.android.volley:volley:1.1.1'`  
`implementation 'com.google.code.gson:gson:2.8.6'`  

### Lab07 - Position