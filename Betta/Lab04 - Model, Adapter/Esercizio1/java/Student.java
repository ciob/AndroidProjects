package com.example.lab04es1;

public class Student {
    private String nome;
    private String cognome;
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
