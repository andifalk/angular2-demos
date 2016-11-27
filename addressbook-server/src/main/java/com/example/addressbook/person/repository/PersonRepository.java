package com.example.addressbook.person.repository;

import com.example.addressbook.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for data access of {@link Person}.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
