package com.example.addressbook.person.boundary;

import com.example.addressbook.person.model.Person;

import java.util.List;

/**
 * Created by afa on 15.08.16.
 */
public interface PersonService {

    List<Person> findAll();

    Person save(Person person);

    Person findOne(Long id);
}
