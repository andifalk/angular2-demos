package com.example.addressbook.person.api.resource;

import com.example.addressbook.person.model.Person;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Created by afa on 15.08.16.
 */
public class PersonResource extends Resource<Person> {

    public PersonResource(Person content, Link... links) {
        super(content, links);
    }
}
