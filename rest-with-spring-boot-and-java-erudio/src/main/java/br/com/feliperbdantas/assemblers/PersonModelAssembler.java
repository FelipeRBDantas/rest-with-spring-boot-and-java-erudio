package br.com.feliperbdantas.assemblers;

import br.com.feliperbdantas.controllers.v1.PersonController;
import br.com.feliperbdantas.data.dto.v1.PersonDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<PersonDTO, EntityModel<PersonDTO>> {

    @Override
    public EntityModel<PersonDTO> toModel(PersonDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).findAll()).withRel("persons")
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
