package com.example.addressbook.person.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created by afa on 15.08.16.
 */
@Entity
public class Person extends AbstractPersistable<Long> {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    public Person() {
    }

    public Person(String firstName, String lastName, GenderEnum gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public GenderEnum getGender() {
        return gender;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return super.isNew();
    }
}
