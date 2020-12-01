package com.example.lab07;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.Arrays;

public class ApplicationModel {
    private static  ApplicationModel ourInstance = null;

    private static ArrayList<Student> lista_studenti;
    private static AppDatabase studentDB =null;

    public static synchronized ApplicationModel getInstance() {
        if (ourInstance==null) {
            ourInstance = new ApplicationModel();
        }
        return ourInstance;
    }

    public static synchronized ApplicationModel getInstance(Context context) {
        if (ourInstance==null) {
            studentDB= Room.databaseBuilder(context, AppDatabase.class, "studentDB").build();
            ourInstance = new ApplicationModel();
        }
        return ourInstance;
    }

    private ApplicationModel() {
        lista_studenti = new ArrayList<Student>();
    }


    //metodi per la gestione della lista degli studenti
    public void addStudents(Student[] s) throws Exception {
        studentDB.studentDAO().insertAll(s);
        lista_studenti.addAll(Arrays.asList(s));
        return;
    }

    public ArrayList<Student> getStudents()
    {
        if(lista_studenti.size()==0)
        {
            lista_studenti.addAll(studentDB.studentDAO().getAll());
        }
        return lista_studenti;
    }

    //per recuperare gli studenti salvati nel db
    public void getStudentsFromDB()
    {
        lista_studenti.addAll(studentDB.studentDAO().getAll());
    }

    //metodi per l'adapter
    public Student getStudentByIndex(int index) {
        return ApplicationModel.getInstance().getStudents().get(index);
    }
    public int getSize() {
        return ApplicationModel.getInstance().getStudents().size();
    }
}
