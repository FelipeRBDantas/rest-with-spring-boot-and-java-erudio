package br.com.feliperbdantas.assembler;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.application.assembler.PersonModelAssembler;
import br.com.feliperbdantas.unittest.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

class PersonModelAssemblerTest {
    MockPerson input;

    PersonModelAssembler assembler;

    @BeforeEach
    void setUp() {
        input = new MockPerson();

        assembler = new PersonModelAssembler();
    }

    @Test
    void toModel_shouldAddHateoasLinks() {
        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);
        dto.setFirstName("Felipe");

        EntityModel<PersonDTO> model = assembler.toModel(dto);

        assertNotNull(model.getContent());
        assertEquals(1L, model.getContent().getId());

        assertTrue(model.getLinks().hasLink("self"));
        assertTrue(model.getLinks().hasLink("findAll"));
        assertTrue(model.getLinks().hasLink("create"));
        assertTrue(model.getLinks().hasLink("update"));
        assertTrue(model.getLinks().hasLink("delete"));
    }

    @Test
    void toFlatList_shouldReturnListWithLinks() {
        List<PersonDTO> dtos = List.of(new PersonDTO(), new PersonDTO());
        List<EntityModel<PersonDTO>> models = assembler.toFlatList(dtos);

        assertEquals(2, models.size());
        models.forEach(m -> assertTrue(m.getLinks().hasLink("self")));
    }
}
