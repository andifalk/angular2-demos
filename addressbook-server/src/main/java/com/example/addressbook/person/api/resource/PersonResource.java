package com.example.addressbook.person.api.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.example.addressbook.person.api.PersonController;
import com.example.addressbook.person.model.GenderEnum;
import com.example.addressbook.person.model.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by afa on 15.08.16.
 */
@JsonPropertyOrder({"id", "gender", "firstName", "lastName"})
public class PersonResource extends ResourceSupport {

    private final Person person;

    public PersonResource(Person person) {
        this.person = person;
        add(linkTo(methodOn(PersonController.class).findOne(person.getId())).withSelfRel());
        add(linkTo(methodOn(PersonController.class).findAll()).withRel("back"));
    }

    public String getFirstName() {
        return person.getFirstName();
    }

    public String getLastName() {
        return person.getLastName();
    }

    public GenderEnum getGender() {
        return person.getGender();
    }

    @JsonProperty("id")
    public Long getPersonId() {
        return person.getId();
    }
}
