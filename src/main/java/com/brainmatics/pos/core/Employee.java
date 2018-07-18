package com.brainmatics.pos.core;


import java.time.LocalDate;

public class Employee {

    private int id;
    private String name;
    private LocalDate birthDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public Employee() {
        this.birthDate = LocalDate.now().minusYears(25);
    }
}
