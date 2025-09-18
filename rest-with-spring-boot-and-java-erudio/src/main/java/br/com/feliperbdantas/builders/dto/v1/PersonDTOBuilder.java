package br.com.feliperbdantas.builders.dto.v1;

import br.com.feliperbdantas.data.dto.v1.PersonDTO;

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

    public PersonDTOBuilder withBasicInfo(String firstName, String lastName, String address, String gender) {
        return this.firstName(firstName)
                .lastName(lastName)
                .address(address)
                .gender(gender);
    }

    public PersonDTO build() {
        return dto;
    }
}
