package br.com.feliperbdantas.builders.dto.v2;

import br.com.feliperbdantas.data.dto.v2.PersonDTOV2;

import java.util.Date;

public class PersonDTOBuilderV2 {
    private final PersonDTOV2 dto;

    private PersonDTOBuilderV2() {
        this.dto = new PersonDTOV2();
    }

    public static PersonDTOBuilderV2 create() {
        return new PersonDTOBuilderV2();
    }

    public PersonDTOBuilderV2 id(Long id) {
        dto.setId(id);

        return this;
    }

    public PersonDTOBuilderV2 firstName(String firstName) {
        dto.setFirstName(firstName);

        return this;
    }

    public PersonDTOBuilderV2 lastName(String lastName) {
        dto.setLastName(lastName);

        return this;
    }

    public PersonDTOBuilderV2 address(String address) {
        dto.setAddress(address);

        return this;
    }

    public PersonDTOBuilderV2 gender(String gender) {
        dto.setGender(gender);

        return this;
    }

    public PersonDTOBuilderV2 birthDay(Date birthDay) {
        dto.setBirthDay(birthDay);

        return this;
    }

    public PersonDTOBuilderV2 withBasicInfo(String firstName, String lastName, String address, String gender, Date birthDay) {
        return this.firstName(firstName)
                .lastName(lastName)
                .address(address)
                .gender(gender)
                .birthDay(birthDay);
    }

    public PersonDTOV2 build() {
        return dto;
    }
}
