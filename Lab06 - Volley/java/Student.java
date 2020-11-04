package com.example.lab06bis;

public class Student {

    private final String name;
    private final String surname;
    private final String id;

    public Student(String name, String surname, String id){
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "name: '" + name + "'\n" +
                "surname: '" + surname + "'\n" +
                "id: '" + id + "'\n";
    }

}
