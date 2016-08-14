package com.example.addressbook.person.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;

import java.util.Collection;

/**
 * Created by afa on 15.08.16.
 */
public class PersonListResource extends Resources<PersonResource> {

    public PersonListResource(Iterable<PersonResource> content, Link... links) {
        super(content, links);
    }

    @JsonIgnore
    @Override
    public Collection<PersonResource> getContent() {
        return super.getContent();
    }

    public Collection<PersonResource> getPersons() {
        return getContent();
    }
}
