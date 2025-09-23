package br.com.feliperbdantas.application.mapper;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.domain.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toDto(Person person);

    Person toEntity(PersonDTO dto);

    List<PersonDTO> toDtoList(List<Person> persons);

    List<Person> toEntityList(List<PersonDTO> dtos);
}

