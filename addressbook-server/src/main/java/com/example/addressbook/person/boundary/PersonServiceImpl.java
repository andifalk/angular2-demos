package com.example.addressbook.person.boundary;

import com.example.addressbook.person.model.Person;
import com.example.addressbook.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link PersonService}.
 */
@Transactional(readOnly = true)
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person findOne(Long id) {
        return personRepository.findOne(id);
    }
}
