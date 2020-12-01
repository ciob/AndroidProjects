package com.example.lab07;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//infine creo la classe astratta per utilizzare le funzionalità di Room
@Database(entities = {Student.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}
