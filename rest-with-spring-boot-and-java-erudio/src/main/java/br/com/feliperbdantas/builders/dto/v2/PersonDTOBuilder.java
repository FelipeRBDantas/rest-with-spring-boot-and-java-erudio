package br.com.feliperbdantas.builders.dto.v2;

import br.com.feliperbdantas.data.dto.v2.PersonDTO;

import java.util.Date;

public class PersonDTOBuilder {
    private final PersonDTO dto;

    private PersonDTOBuilder() {
        this.dto = new PersonDTO();
    }

    public static PersonDTOBuilder create() {
        return new PersonDTOBuilder();
    }

    public PersonDTOBuilder id(Long id) {
        dto.setId(id);

        return this;
    }

    public PersonDTOBuilder firstName(String firstName) {
        dto.setFirstName(firstName);

        return this;
    }

    public PersonDTOBuilder lastName(String lastName) {
        dto.setLastName(lastName);

        return this;
    }

    public PersonDTOBuilder address(String address) {
        dto.setAddress(address);

        return this;
    }

    public PersonDTOBuilder gender(String gender) {
        dto.setGender(gender);

        return this;
    }

    public PersonDTOBuilder birthDay(Date birthDay) {
        dto.setBirthDay(birthDay);

        return this;
    }

    public PersonDTOBuilder withBasicInfo(String firstName, String lastName, String address, String gender, Date birthDay) {
        return this.firstName(firstName)
                .lastName(lastName)
                .address(address)
                .gender(gender)
                .birthDay(birthDay);
    }

    public PersonDTO build() {
        return dto;
    }
}
