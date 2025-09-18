package br.com.feliperbdantas.mapper.custom;

import br.com.feliperbdantas.builders.dto.v1.PersonDTOBuilder;
import br.com.feliperbdantas.builders.dto.v2.PersonDTOBuilderV2;
import br.com.feliperbdantas.builders.model.v1.PersonBuilder;
import br.com.feliperbdantas.builders.model.v2.PersonBuilderV2;
import br.com.feliperbdantas.data.dto.v1.PersonDTO;
import br.com.feliperbdantas.data.dto.v2.PersonDTOV2;
import br.com.feliperbdantas.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonMapper {
    public PersonDTO convertEntityToDTO(Person person) {
        return PersonDTOBuilder.create()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .gender(person.getGender())
                .build();
    }

    public PersonDTOV2 convertEntityToDTOV2(Person person) {
        return PersonDTOBuilderV2.create()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .birthDay(new Date())
                .address(person.getAddress())
                .gender(person.getGender())
                .build();
    }

    public Person convertDTOToEntity(PersonDTO person) {
        return PersonBuilder.create()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .gender(person.getGender())
                .build();
    }

    public Person convertDTOToEntityV2(PersonDTOV2 person) {
        return PersonBuilderV2.create()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                /* .birthDay(new Date()) */
                .address(person.getAddress())
                .gender(person.getGender())
                .build();
    }

    public Person updateEntityFromDTO(PersonDTO personDTO, Person person) {
        return PersonBuilder.from(person)
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .address(personDTO.getAddress())
                .gender(personDTO.getGender())
                .build();
    }

    public Person updateEntityFromDTOV2(PersonDTOV2 personDTO, Person person) {
        return PersonBuilderV2.from(person)
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                /* .birthDay(new Date()) */
                .address(personDTO.getAddress())
                .gender(personDTO.getGender())
                .build();
    }

    public Set<PersonDTO> convertEntitiesToDTOs(List<Person> persons) {
        return persons.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<PersonDTOV2> convertEntitiesToDTOsV2(List<Person> persons) {
        return persons.stream()
                .map(this::convertEntityToDTOV2)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<Person> convertDTOsToEntities(List<PersonDTO> persons) {
        return persons.stream()
                .map(this::convertDTOToEntity)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<Person> convertDTOsToEntitiesV2(List<PersonDTOV2> persons) {
        return persons.stream()
                .map(this::convertDTOToEntityV2)
                .collect(Collectors.toUnmodifiableSet());
    }
}
