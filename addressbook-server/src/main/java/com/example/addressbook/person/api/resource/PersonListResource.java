package com.example.addressbook.person.api.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.example.addressbook.person.api.PersonController;
import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

/**
 * Created by afa on 15.08.16.
 */
public class PersonListResource extends ResourceSupport {

    private final Collection<PersonResource> persons;

    public PersonListResource(Collection<PersonResource> persons) {
        this.persons = persons;
        add(linkTo(methodOn(PersonController.class).findAll()).withSelfRel());
    }

    public Collection<PersonResource> getPersons() {
        return persons;
    }
}
