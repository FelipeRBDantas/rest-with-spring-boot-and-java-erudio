package br.com.feliperbdantas.application.mapper.custom.v1;

import br.com.feliperbdantas.application.builder.dto.v1.PersonDTOBuilder;
import br.com.feliperbdantas.application.builder.model.v1.PersonBuilder;
import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.domain.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("personMapperV1")
public class PersonMapper {
    public PersonDTO convertEntityToDTO(Person person) {
        return PersonDTOBuilder.create()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .gender(person.getGender())
                .build();
    }

    public Person convertDTOToEntity(PersonDTO person) {
        return PersonBuilder.create()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
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
    public PersonDTO updateDTOFromEntity(Person person, PersonDTO personDTO) {
        return PersonDTOBuilder.from(personDTO)
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .gender(person.getGender())
                .build();
    }

    public List<PersonDTO> convertEntitiesToDTOs(List<Person> persons) {
        return persons.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<Person> updateEntitiesFromDTOs(List<PersonDTO> persons, List<Person> existingPersons) {
        return IntStream.range(0, persons.size())
                .mapToObj(i -> updateEntityFromDTO(persons.get(i), existingPersons.get(i)))
                .collect(Collectors.toList());
    }
}
