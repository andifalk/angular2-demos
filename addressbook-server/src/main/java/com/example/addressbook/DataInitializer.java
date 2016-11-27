package com.example.addressbook;

import com.example.addressbook.person.model.GenderEnum;
import com.example.addressbook.person.model.Person;
import com.example.addressbook.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Initializer for test data.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Autowired
    public DataInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of(
                new Person("Hans", "Mustermann", GenderEnum.MALE),
                new Person("Heinz", "Meiser", GenderEnum.MALE),
                new Person("Sabine", "Hermann", GenderEnum.FEMALE))
                .forEach(personRepository::save);
    }
}
