package br.com.feliperbdantas.interfaces.assembler.v1;

import br.com.feliperbdantas.application.dto.v1.BookDTO;
import br.com.feliperbdantas.application.presenter.v1.BookPresenter;
import br.com.feliperbdantas.interfaces.rest.controller.v1.BookControllerImpl;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookHateoasAssembler implements BookPresenter {
    @Override
    public BookDTO present(BookDTO dto) {
        addLinks(dto);

        return dto;
    }

    @Override
    public List<BookDTO> present(List<BookDTO> dtos) {
        return dtos.stream()
                .peek(this::addLinks)
                .toList();
    }

    private void addLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookControllerImpl.class).findById(dto.getId())).withSelfRel());
        dto.add(linkTo(methodOn(BookControllerImpl.class).findAll()).withRel("findAll"));
        dto.add(linkTo(methodOn(BookControllerImpl.class).create(dto)).withRel("create"));
        dto.add(linkTo(methodOn(BookControllerImpl.class).update(dto)).withRel("update"));
        dto.add(linkTo(methodOn(BookControllerImpl.class).delete(dto.getId())).withRel("delete"));
    }
}
