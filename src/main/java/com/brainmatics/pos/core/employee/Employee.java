package com.brainmatics.pos.core.employee;


import com.brainmatics.common.EntityBase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employee extends EntityBase {

    private String code;
    private String name;
    private LocalDate birthDate;

    @Embedded
    private Address home;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Address getHome() {
        return home;
    }

    public void setHome(Address home) {
        this.home = home;
    }

    public Employee() {
        this.birthDate = LocalDate.now().minusYears(25);
    }
}
