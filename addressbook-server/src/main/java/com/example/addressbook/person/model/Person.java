package com.example.addressbook.person.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Person entity.
 */
@Entity
public class Person extends AbstractPersistable<Long> {

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9- ]{1,30}$")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9- ]{1,30}$")
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    /**
     * Default constructor
     */
    @SuppressWarnings("unused")
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
