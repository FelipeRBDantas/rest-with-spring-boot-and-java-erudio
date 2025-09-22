package br.com.feliperbdantas.services.v1.impl;

import br.com.feliperbdantas.data.dto.v1.PersonDTO;
import br.com.feliperbdantas.exception.RequiredObjectIsNullException;
import br.com.feliperbdantas.mappers.custom.v1.PersonMapper;
import br.com.feliperbdantas.models.Person;
import br.com.feliperbdantas.repository.PersonRepository;
import br.com.feliperbdantas.unittests.mapper.mocks.MockPerson;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

class PersonServiceImplTest {
    MockPerson input;

    @Mock
    PersonRepository repository;

    @Mock
    PersonMapper mapper;

    @InjectMocks
    PersonServiceImpl service;

    @BeforeEach
    void setUp() {
        input = new MockPerson();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Person> personsEntity = input.mockEntityList();

        when(repository.findAll()).thenReturn(personsEntity);

        List<PersonDTO> personsDTO = service.findAll();

        assertNotNull(personsDTO);
        assertEquals(14, personsDTO.size());

        var personOne = personsDTO.get(1);

        assertNotNull(personOne);
        assertEquals(1, personOne.getId());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());
        assertEquals("Address Test1", personOne.getAddress());

        var personFour = personsDTO.get(4);

        assertNotNull(personFour);
        assertEquals(4, personFour.getId());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());
        assertEquals("Address Test4", personFour.getAddress());
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
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
        assertEquals("Address Test1", result.getAddress());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> {
                    service.create(null);
                });

        String expectedMessage = "It is not allowed to persist a null object.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(mapper.updateEntityFromDTO(dto, person)).thenReturn(person);
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
        assertEquals("Address Test1", result.getAddress());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> {
                    service.update(null);
                });

        String expectedMessage = "It is not allowed to persist a null object.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }
}