package br.com.feliperbdantas.interfaces.assembler.v1;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.application.presenter.v1.PersonPresenter;
import br.com.feliperbdantas.interfaces.rest.controller.v1.PersonController;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PersonHateoasAssembler implements PersonPresenter {
    @Override
    public PersonDTO present(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel());
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete"));

        return dto;
    }

    @Override
    public List<PersonDTO> present(List<PersonDTO> dtos) {
        return null;
    }
}
