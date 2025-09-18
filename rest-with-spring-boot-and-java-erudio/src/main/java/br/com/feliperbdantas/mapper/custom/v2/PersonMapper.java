package br.com.feliperbdantas.mapper.custom.v2;

import br.com.feliperbdantas.builders.dto.v2.PersonDTOBuilder;
import br.com.feliperbdantas.builders.model.v2.PersonBuilder;
import br.com.feliperbdantas.data.dto.v2.PersonDTO;
import br.com.feliperbdantas.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("personMapperV2")
public class PersonMapper {
    public PersonDTO convertEntityToDTO(Person person) {
        return PersonDTOBuilder.create()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .birthDay(new Date())
                .address(person.getAddress())
                .gender(person.getGender())
                .build();
    }

    public Person convertDTOToEntity(PersonDTO person) {
        return PersonBuilder.create()
                .id(person.getId())
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

    public Set<Person> updateEntityFromDTO(List<PersonDTO> persons, List<Person> existingPersons) {
        return IntStream.range(0, persons.size())
                .mapToObj(i -> updateEntityFromDTO(persons.get(i), existingPersons.get(i)))
                .collect(Collectors.toUnmodifiableSet());
    }
}
