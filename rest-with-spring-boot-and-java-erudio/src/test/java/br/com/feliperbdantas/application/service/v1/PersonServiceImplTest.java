package br.com.feliperbdantas.application.service.v1;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.application.presenter.v1.PersonPresenter;
import br.com.feliperbdantas.exception.RequiredObjectIsNullException;
import br.com.feliperbdantas.application.mapper.PersonMapper;
import br.com.feliperbdantas.domain.model.Person;
import br.com.feliperbdantas.infrastructure.persistence.repository.PersonRepository;
import br.com.feliperbdantas.application.mapper.mocks.MockPerson;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.Optional;

class PersonServiceImplTest {
    MockPerson input;

    @Mock
    PersonRepository repository;

    @Mock
    PersonMapper mapper;

    @Mock
    PersonPresenter presenter;

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

        PersonDTO personDTO = input.mockDTO(1);

        String pathRoot = "http://localhost:8080";

        PersonDTO personDTOWithHateoas = input.mockDTO(1);
        personDTOWithHateoas.setId(1L);
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person/1")).withSelfRel());
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person")).withRel("findAll"));
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person")).withRel("create"));
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person")).withRel("update"));
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person/1")).withRel("delete"));

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        when(mapper.toDto(person)).thenReturn(personDTO);

        when(presenter.present(personDTO)).thenReturn(personDTOWithHateoas);

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/v1/person/1"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/v1/person"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/v1/person"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/v1/person"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/v1/person/1"))
        );

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void create() {
        Person person = input.mockEntity(1);

        Person persisted = person;
        persisted.setId(1L);

        PersonDTO personDTO = input.mockDTO(1);

        String pathRoot = "http://localhost:8080";

        PersonDTO personDTOWithHateoas = input.mockDTO(1);
        personDTOWithHateoas.setId(1L);
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person/1")).withSelfRel());
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person")).withRel("findAll"));
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person")).withRel("create"));
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person")).withRel("update"));
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person/1")).withRel("delete"));

        when(mapper.toEntity(personDTO)).thenReturn(person);

        when(repository.save(person)).thenReturn(persisted);

        when(mapper.toDto(person)).thenReturn(personDTO);

        when(presenter.present(personDTO)).thenReturn(personDTOWithHateoas);

        var result = service.create(personDTO);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                                && link.getHref().endsWith("/api/v1/person/1"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                                && link.getHref().endsWith("/api/v1/person"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/v1/person"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                                && link.getHref().endsWith("/api/v1/person"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/v1/person/1"))
        );

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> {
                    service.create(null);
                });

        String expectMessage = "It is not allowed to persist a null object.";

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectMessage));
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);

        Person persisted = person;
        persisted.setId(1L);

        PersonDTO personDTO = input.mockDTO(1);

        String pathRoot = "http://localhost:8080";

        PersonDTO personDTOWithHateoas = input.mockDTO(1);
        personDTOWithHateoas.setId(1L);
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person/1")).withSelfRel());
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person")).withRel("findAll"));
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person")).withRel("create"));
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person")).withRel("update"));
        personDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/person/1")).withRel("delete"));

        when(mapper.toEntity(personDTO)).thenReturn(person);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        when(repository.save(person)).thenReturn(persisted);

        when(mapper.toDto(person)).thenReturn(personDTO);

        when(presenter.present(personDTO)).thenReturn(personDTOWithHateoas);

        var result = service.update(personDTO);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                                && link.getHref().endsWith("/api/v1/person/1"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                                && link.getHref().endsWith("/api/v1/person"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/v1/person"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                                && link.getHref().endsWith("/api/v1/person"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/v1/person/1"))
        );

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> {
                    service.update(null);
                });

        String expectMessage = "It is not allowed to persist a null object.";

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectMessage));
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