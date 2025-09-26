package br.com.feliperbdantas.application.service.v1.contract;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> findAll();
    PersonDTO findById(Long id);
    PersonDTO create(PersonDTO person);
    PersonDTO update(PersonDTO person);
    void delete(Long id);
}
