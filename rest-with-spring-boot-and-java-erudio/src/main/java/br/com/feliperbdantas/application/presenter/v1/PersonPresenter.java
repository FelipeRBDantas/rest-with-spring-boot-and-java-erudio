package br.com.feliperbdantas.application.presenter.v1;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;

import java.util.List;

public interface PersonPresenter {
    PersonDTO present(PersonDTO dto);
    List<PersonDTO> present(List<PersonDTO> dtos);
}
