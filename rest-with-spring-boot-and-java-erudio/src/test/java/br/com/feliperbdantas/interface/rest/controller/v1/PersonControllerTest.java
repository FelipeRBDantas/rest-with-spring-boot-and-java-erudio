package br.com.feliperbdantas.controller.v1;

import br.com.feliperbdantas.application.assembler.PersonModelAssembler;
import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.application.service.v1.PersonServiceImpl;
import br.com.feliperbdantas.unittest.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonControllerTest {
    MockPerson input;

    @Mock
    PersonModelAssembler assembler;

    @Mock
    PersonServiceImpl service;

    @InjectMocks
    br.com.feliperbdantas.controller.v1.PersonController controller;

    @BeforeEach
    void setUp() {
        input = new MockPerson();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<PersonDTO> personsDTO = input.mockDTOList();

        var personOne = personsDTO.get(1);

        List<EntityModel<PersonDTO>> models = personsDTO.stream()
                .map(p -> EntityModel.of(
                        p,
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).findById(p.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).findAll()).withRel("findAll"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).create(p)).withRel("create"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).update(p)).withRel("update"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).delete(p.getId())).withRel("delete")
                ))
                .toList();

        when(service.findAll()).thenReturn(personsDTO);

        when(assembler.toFlatList(personsDTO)).thenReturn(models);

        assertNotNull(personsDTO);
        assertEquals(14, personsDTO.size());

        assertNotNull(personOne);
        assertEquals(1, personOne.getId());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());
        assertEquals("Address Test1", personOne.getAddress());

        ResponseEntity<List<EntityModel<PersonDTO>>> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(14, response.getBody().size());

        EntityModel<PersonDTO> modelOne = response.getBody().get(1);

        assertNotNull(modelOne);
        assertNotNull(modelOne.getContent());
        assertNotNull(modelOne.getLinks());

        assertTrue(
                modelOne.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("self")
                                        && link.getHref().endsWith("/v1/person/1")
                        )
        );

        assertTrue(
                modelOne.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("findAll")
                                        && link.getHref().endsWith("/v1/person")
                        )
        );

        assertTrue(
                modelOne.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("create")
                                        && link.getHref().endsWith("/v1/person")
                        )
        );

        assertTrue(
                modelOne.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("update")
                                        && link.getHref().endsWith("/v1/person")
                        )
        );

        assertTrue(
                modelOne.getLinks().stream()
                        .anyMatch(
                                link -> link.getRel().value().equals("delete")
                                        && link.getHref().endsWith("/v1/person/1")
                        )
        );

        assertEquals(1, modelOne.getContent().getId());
        assertEquals("First Name Test1", modelOne.getContent().getFirstName());
        assertEquals("Last Name Test1", modelOne.getContent().getLastName());
        assertEquals("Female", modelOne.getContent().getGender());
        assertEquals("Address Test1", modelOne.getContent().getAddress());
    }

    @Test
    void findById() {
        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(service.findById(1L)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(
                EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).findById(dto.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).findAll()).withRel("findAll"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).create(dto)).withRel("create"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).update(dto)).withRel("update"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).delete(dto.getId())).withRel("delete")
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
        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(service.create(dto)).thenReturn(dto);
        when(assembler.toModel(dto)).thenReturn(
                EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).findById(dto.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).findAll()).withRel("findAll"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).create(dto)).withRel("create"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).update(dto)).withRel("update"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).delete(dto.getId())).withRel("delete")
                )
        );

        ResponseEntity<PersonDTO> response = controller.create(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());

        EntityModel<PersonDTO> model = assembler.toModel(response.getBody());

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
    void update() {
        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(service.update(dto)).thenReturn(dto);

        when(assembler.toModel(dto)).thenReturn(
                EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).findById(dto.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).findAll()).withRel("findAll"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).create(dto)).withRel("create"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).update(dto)).withRel("update"),
                        WebMvcLinkBuilder.linkTo(methodOn(br.com.feliperbdantas.controller.v1.PersonController.class).delete(dto.getId())).withRel("delete")
                )
        );

        ResponseEntity<EntityModel<PersonDTO>> response = controller.update(dto);

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
    void delete() {
        doNothing().when(service).delete(1L);

        ResponseEntity<Void> response = controller.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).delete(1L);
    }
}