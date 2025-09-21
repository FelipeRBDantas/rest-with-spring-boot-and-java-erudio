package br.com.feliperbdantas.controllers.v1;

import br.com.feliperbdantas.assemblers.PersonModelAssembler;
import br.com.feliperbdantas.data.dto.v1.PersonDTO;
import br.com.feliperbdantas.services.v1.impl.PersonServiceImpl;
import br.com.feliperbdantas.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class PersonControllerTest {
    MockPerson input;

    @Mock
    PersonModelAssembler assembler;

    @Mock
    PersonServiceImpl service;

    @InjectMocks
    PersonController controller;

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
        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(service.findById(1L)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(
                EntityModel.of(dto,
                        linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel(),
                        linkTo(methodOn(PersonController.class).findAll()).withRel("findAll"),
                        linkTo(methodOn(PersonController.class).create(dto)).withRel("create"),
                        linkTo(methodOn(PersonController.class).update(dto)).withRel("update"),
                        linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete")
                )
        );

        ResponseEntity<EntityModel<PersonDTO>> response = controller.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        EntityModel<PersonDTO> model = response.getBody();

        assertNotNull(model);
        assertNotNull(model.getContent());
        assertEquals(1L, model.getContent().getId());
        assertNotNull(model.getLinks());

        assertTrue(
                model.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("self")
                                        && link.getHref().endsWith("/v1/person/1")
                        )
        );

        assertTrue(
                model.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("findAll")
                                        && link.getHref().endsWith("/v1/person")
                        )
        );

        assertTrue(
                model.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("create")
                                        && link.getHref().endsWith("/v1/person")
                        )
        );

        assertTrue(
                model.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("update")
                                        && link.getHref().endsWith("/v1/person")
                        )
        );

        assertTrue(
                model.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("delete")
                                        && link.getHref().endsWith("/v1/person/1")
                        )
        );

        assertEquals(1, model.getContent().getId());
        assertEquals("First Name Test1", model.getContent().getFirstName());
        assertEquals("Last Name Test1", model.getContent().getLastName());
        assertEquals("Female", model.getContent().getGender());
        assertEquals("Address Test1", model.getContent().getAddress());
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