package br.com.feliperbdantas.interfaces.assembler.v1;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.application.presenter.v1.PersonPresenter;
import br.com.feliperbdantas.interfaces.rest.controller.v1.PersonControllerImpl;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PersonHateoasAssembler implements PersonPresenter {
    @Override
    public PersonDTO present(PersonDTO dto) {
        addLinks(dto);

        return dto;
    }

    @Override
    public List<PersonDTO> present(List<PersonDTO> dtos) {
        return dtos.stream()
                .peek(this::addLinks)
                .toList();
    }

    private void addLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonControllerImpl.class).findById(dto.getId())).withSelfRel());
        dto.add(linkTo(methodOn(PersonControllerImpl.class).findAll()).withRel("findAll"));
        dto.add(linkTo(methodOn(PersonControllerImpl.class).create(dto)).withRel("create"));
        dto.add(linkTo(methodOn(PersonControllerImpl.class).update(dto)).withRel("update"));
        dto.add(linkTo(methodOn(PersonControllerImpl.class).delete(dto.getId())).withRel("delete"));
    }
}
