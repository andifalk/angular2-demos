package com.example.addressbook.person.api;

import com.example.addressbook.person.api.resource.ModifyPersonResource;
import com.example.addressbook.person.boundary.PersonService;
import com.example.addressbook.person.model.GenderEnum;
import com.example.addressbook.person.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.security.oauth2.client.test.OAuth2ContextSetup;
import org.springframework.security.oauth2.client.test.RestTemplateHolder;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestOperations;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test to verify {@link PersonController}.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = PersonController.class)
@WithMockUser
//@OAuth2ContextConfiguration(ResourceOwnerPasswordResourceDetails.class)
public class PersonControllerTest implements RestTemplateHolder {

    /*@Rule
    public OAuth2ContextSetup context = OAuth2ContextSetup.withEnvironment(this, MyClientDetailsResource.instance());*/

    private RestOperations restTemplate;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("unused")
    @MockBean
    private PersonService personService;

    /**
     * Init all mocks.
     */
    @Before
    public void setup() {
        Person person1 = new Person("Hans", "Mustermann", GenderEnum.MALE);
        Person person2 = new Person("Sabine", "Huber", GenderEnum.FEMALE);
        ReflectionTestUtils.setField(person1, "id", 1L);
        ReflectionTestUtils.setField(person2, "id", 2L);
        when(personService.findAll()).thenReturn(Arrays.asList(person1, person2));
        when(personService.findOne(1L)).thenReturn(person1);
        when(personService.findOne(2L)).thenReturn(person2);
        when(personService.save(any(Person.class))).thenAnswer(invocation -> {
            assertThat(invocation.getArguments()).isNotNull().hasSize(1);
            Person person = invocation.getArgumentAt(0, Person.class);
            ReflectionTestUtils.setField(person, "id", 1L);
            return person;
        });
    }

    /**
     * Verify finding all persons.
     * @throws Exception not expected
     */
    @Test
    public void verifyFindAll() throws Exception {
        mockMvc.perform(
                get("/persons").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons").isArray())
                .andExpect(jsonPath("$.persons").isNotEmpty())
                .andExpect(jsonPath("$.persons[0].firstName").value(is("Hans")));
    }

    /**
     * Verify finding one person.
     * @throws Exception not expected
     */
    @Test
    public void verifyFindOne() throws Exception {
        mockMvc.perform(
                get("/persons/1").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(is("Hans")));
    }

    /**
     * Verify finding one person having status 'not found'.
     * @throws Exception not expected
     */
    @Test
    public void verifyFindOneNotFound() throws Exception {
        mockMvc.perform(
                get("/persons/99").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    /**
     * Verify creating a new person.
     * @throws Exception not expected
     */
    @Test
    public void verifyCreatePerson() throws Exception {
        ModifyPersonResource modifyPersonResource = new ModifyPersonResource();
        modifyPersonResource.setFirstName("Hans");
        modifyPersonResource.setLastName("Maier");
        modifyPersonResource.setGender(GenderEnum.MALE.name());
        mockMvc.perform(
                post("/persons")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(modifyPersonResource)))
                .andExpect(status().isCreated());
    }

    @Override
    public void setRestTemplate(RestOperations restTemplate) {

    }

    @Override
    public RestOperations getRestTemplate() {
        return null;
    }

    static class MyClientDetailsResource extends
            ResourceOwnerPasswordResourceDetails {
  		public MyClientDetailsResource(Environment environment) {

        }
  	}


}