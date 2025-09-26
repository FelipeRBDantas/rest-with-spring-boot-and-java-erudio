package br.com.feliperbdantas.application.mapper;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.domain.model.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toDto(Person person);

    Person toEntity(PersonDTO dto);

    List<PersonDTO> toDtoList(List<Person> persons);

    List<Person> toEntityList(List<PersonDTO> dtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(PersonDTO dto, @MappingTarget Person entity);
}

