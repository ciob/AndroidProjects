package com.example.lab06bis;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class StudentModel {

    private StudentList studentList;
    private static StudentModel instance = null;

    private StudentModel (){
        studentList = new StudentList();
    }

    public static synchronized StudentModel getInstance(){
        if (instance == null){
            instance = new StudentModel();
        }
        return instance;
    }

    public void createStudents(String response){
        Gson g = new Gson();
        studentList = g.fromJson(response, StudentList.class);
    }

    public Student getStudent(int position){
        return studentList.getStudent(position);
    }

    public int getSize(){
        return studentList.getSize();
    }
}
