package com.example.addressbook.person.boundary;

import com.example.addressbook.person.model.GenderEnum;
import com.example.addressbook.person.model.Person;
import com.example.addressbook.person.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link PersonService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    private PersonService cut;

    private Person person1;
    private Person person2;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void init() {
        cut = new PersonServiceImpl(personRepository);

        person1 = new Person("Hans", "Mustermann", GenderEnum.MALE);
        person2 = new Person("Sabine", "Maier", GenderEnum.FEMALE);
        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));
        when(personRepository.findOne(eq(1L))).thenReturn(person1);
    }

    @Test
    public void verifyFindAll() throws Exception {
        List<Person> personList = cut.findAll();
        assertThat(personList).isNotNull().contains(person1, person2);
        verify(personRepository).findAll();
    }

    @Test
    public void verifySave() throws Exception {

        Person person = new Person("Herbert", "Maier", GenderEnum.MALE);

        when(personRepository.save(eq(person))).thenAnswer(invocation -> {
            assertThat(invocation.getArguments().length).isEqualTo(1);
            Person person3 = invocation.getArgumentAt(0, Person.class);
            ReflectionTestUtils.setField(person3, "id", 1L);
            return person3;
        });

        Person storedPerson = cut.save(person);
        assertThat(storedPerson).isNotNull();
        assertThat(storedPerson.getGender()).isNotNull().isEqualTo(person.getGender());
        assertThat(storedPerson.getFirstName()).isNotNull().isEqualTo(person.getFirstName());
        assertThat(storedPerson.getLastName()).isNotNull().isEqualTo(person.getLastName());
        verify(personRepository).save(eq(person));
        verifyNoMoreInteractions(personRepository);
    }

    @Test
    public void verifyFindOne() throws Exception {
        Person person = cut.findOne(1L);
        assertThat(person).isNotNull().isEqualTo(person1);
        verify(personRepository).findOne(eq(1L));
    }

    @Test
    public void verifyFindOneNotFound() throws Exception {
        Person person = cut.findOne(2L);
        assertThat(person).isNull();
        verify(personRepository).findOne(eq(2L));
    }
}