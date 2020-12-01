package com.example.lab07;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//aggiungiamo le annotazioni per la creazione del database

@Entity
public class Student {
    @ColumnInfo(name="name")
    private String nome;

    @ColumnInfo(name="surname")
    private String cognome;

    //la matricola è l'id
    @PrimaryKey
    @NonNull
    private String matricola;

    public Student(String nome, String cognome, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMatricola() {
        return matricola;
    }

    @Override
    public String toString(){
        return this.getNome()+" "+this.getCognome()+" "+this.getMatricola();
    }

}
