package com.example.addressbook.person.boundary;

import com.example.addressbook.person.model.Person;

import java.util.List;

/**
 * Service for managing {@link Person persons}.
 */
public interface PersonService {

    List<Person> findAll();

    Person save(Person person);

    Person findOne(Long id);
}
