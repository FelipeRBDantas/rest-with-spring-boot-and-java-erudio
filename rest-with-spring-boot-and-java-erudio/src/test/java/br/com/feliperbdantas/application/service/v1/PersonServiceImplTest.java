package br.com.feliperbdantas.application.service.v1;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
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

import java.util.List;
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

    }

    @Test
    void create() {

    }

    @Test
    void testCreateWithNullPerson() {

    }

    @Test
    void update() {

    }

    @Test
    void testUpdateWithNullPerson() {

    }

    @Test
    void delete() {

    }
}