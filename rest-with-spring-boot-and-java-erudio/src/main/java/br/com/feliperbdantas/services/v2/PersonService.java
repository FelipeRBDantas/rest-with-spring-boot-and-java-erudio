package br.com.feliperbdantas.services.v2;

import br.com.feliperbdantas.data.dto.v2.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> findAll();
    PersonDTO findById(Long id);
    PersonDTO create(PersonDTO person);
    PersonDTO update(PersonDTO person);
    void delete(Long id);
}
