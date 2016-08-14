package com.example.addressbook.person.api;

import com.example.addressbook.person.api.resource.PersonListResource;
import com.example.addressbook.person.api.resource.PersonResource;
import com.example.addressbook.person.boundary.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by afa on 15.08.16.
 */
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
        List<PersonResource> personResourceList = personService.findAll().stream().map(PersonResource::new).collect(Collectors
                .toList());
        return new PersonListResource(personResourceList);
    }

}
