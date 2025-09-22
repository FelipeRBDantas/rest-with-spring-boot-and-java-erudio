package br.com.feliperbdantas.mappers.v1.assemblers;

import br.com.feliperbdantas.controllers.v1.PersonController;
import br.com.feliperbdantas.data.dto.v1.PersonDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<PersonDTO, EntityModel<PersonDTO>> {
    @Override
    public EntityModel<PersonDTO> toModel(PersonDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(PersonController.class).findAll()).withRel("findAll"),
                linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).create(dto)).withRel("create"),
                linkTo(methodOn(PersonController.class).update(dto)).withRel("update"),
                linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete")
        );
    }

    public List<EntityModel<PersonDTO>> toFlatList(Iterable<? extends PersonDTO> dtos) {
        List<EntityModel<PersonDTO>> result = new ArrayList<>();

        for (PersonDTO dto : dtos) {
            result.add(toModel(dto));
        }

        return result;
    }
}
