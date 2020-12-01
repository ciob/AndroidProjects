package com.example.lab07;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

//implemento il DAO

@Dao
public interface StudentDAO {

    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Insert
    void insertAll(Student... users);

}
