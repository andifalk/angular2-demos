package com.example.addressbook.person.api.resource;

import javax.validation.constraints.NotNull;

/**
 * Created by afa on 15.08.16.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifyPersonResource {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String gender;

    public ModifyPersonResource() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
