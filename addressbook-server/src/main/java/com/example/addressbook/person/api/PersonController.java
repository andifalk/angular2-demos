package com.example.addressbook.person.api;

import com.example.addressbook.person.api.resource.ModifyPersonResource;
import com.example.addressbook.person.api.resource.PersonListResource;
import com.example.addressbook.person.api.resource.PersonResource;
import com.example.addressbook.person.boundary.PersonService;
import com.example.addressbook.person.model.GenderEnum;
import com.example.addressbook.person.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

/**
 * Created by afa on 15.08.16.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/persons", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public PersonListResource findAll() {
        List<PersonResource> personResourceList =
                personService.findAll().stream().map(PersonResource::new).collect(Collectors.toList());
        return new PersonListResource(personResourceList);
    }

    @RequestMapping(path = "/{personId}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable("personId") Long personId) {
        Person person = personService.findOne(personId);
        if (person == null) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(new PersonResource(person), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createPerson(@Valid @RequestBody ModifyPersonResource personResource) {
        Person person = personService.save(
                new Person(personResource.getFirstName(), personResource.getLastName(),
                        GenderEnum.valueOf(personResource.getGender())));
        return new ResponseEntity<>(new PersonResource(person), HttpStatus.CREATED);
    }

}
