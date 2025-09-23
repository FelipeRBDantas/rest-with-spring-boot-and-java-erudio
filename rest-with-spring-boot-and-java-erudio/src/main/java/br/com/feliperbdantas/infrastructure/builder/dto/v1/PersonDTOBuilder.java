package br.com.feliperbdantas.infrastructure.builder.dto.v1;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;

public class PersonDTOBuilder {
    private final PersonDTO dto;

    private PersonDTOBuilder(PersonDTO dto) {
        this.dto = (dto != null) ? dto : new PersonDTO();
    }

    public static PersonDTOBuilder create() {
        return new PersonDTOBuilder(null);
    }

    public static PersonDTOBuilder from(PersonDTO existingDTO) {
        return new PersonDTOBuilder(existingDTO);
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
