package com.example.addressbook.person.repository;

import com.example.addressbook.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by afa on 15.08.16.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
