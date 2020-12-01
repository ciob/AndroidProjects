package com.example.lab04es4;

import android.util.Log;

import java.util.ArrayList;

//IMPLEMENTO IL MODEL CON IL PATTERN SINGLETON
public class ApplicationModel {
    private static  ApplicationModel ourInstance = null;

    private static ArrayList<Student> lista_studenti;

    public static synchronized ApplicationModel getInstance() {
        if (ourInstance==null)
            ourInstance = new ApplicationModel();
        return ourInstance;
    }

    private ApplicationModel() {
        lista_studenti = new ArrayList<Student>();
        //AGGIUNGO UN PO' DI STUDENTI
        lista_studenti.add(new Student("Elisabetta", "Rocchetti", "908859"));
        lista_studenti.add(new Student("Sara", "Bianchi", "908869"));
        lista_studenti.add(new Student("Mario", "Rossi", "934859"));
        lista_studenti.add(new Student("Giovanni", "Verdi", "904329"));
        lista_studenti.add(new Student("Gianna", "Sarti", "945829"));
        lista_studenti.add(new Student("Sofia", "Conti", "458859"));
        lista_studenti.add(new Student("Giorgio", "Riccardi", "905569"));
        lista_studenti.add(new Student("Pippa", "Neri", "508859"));
    }

    //metodi per la gestione della lista degli studenti
    public void addStudent(Student s) throws Exception {
        for (Student item: lista_studenti ) {
            if (s.getMatricola().compareTo(item.getMatricola())==0) throw new Exception("Matricola già esistente");
        }
        lista_studenti.add(s);
        return;
    }

    public ArrayList<Student> getStudents(){
        return lista_studenti;
    }

    public Student getStudentByMatricola(String matricola) throws Exception {
        for (Student s: ApplicationModel.getInstance().getStudents()) {
            Log.d("StudentDetailsFragment", "Studente: ".concat(s.getCognome()));
            if(s.getMatricola().compareTo(matricola)==0){
                return s;
            }
        }
        throw new Exception("Matricola non trovata");
    }

    //metodi per l'adapter
    public Student getStudentByIndex(int index) {
        return ApplicationModel.getInstance().getStudents().get(index);
    }
    public int getSize() {
        return ApplicationModel.getInstance().getStudents().size();
    }
}
