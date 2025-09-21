package br.com.feliperbdantas.services.v1.impl;

import br.com.feliperbdantas.models.Person;
import br.com.feliperbdantas.repository.PersonRepository;
import br.com.feliperbdantas.unittests.mapper.mocks.MockPerson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class PersonServiceImplTest {
    MockPerson input;

    @Mock
    PersonRepository repository;

    @InjectMocks
    PersonServiceImpl service;

    @BeforeEach
    void setUp() {
        input = new MockPerson();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
        assertEquals("Address Test1", result.getAddress());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}