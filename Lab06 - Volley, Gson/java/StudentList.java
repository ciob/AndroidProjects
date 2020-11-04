package com.example.lab06bis;

import java.util.ArrayList;
import java.util.List;

public class StudentList {

    private List<Student> students;

    public StudentList(){
        students = new ArrayList<>();
    }

    public int getSize(){
        return students.size();
    }

    public Student getStudent(int position) {
        return students.get(position);
    }

    @Override
    public String toString() {
        return "StudentList{" +
                "students=" + students +
                '}';
    }
}
