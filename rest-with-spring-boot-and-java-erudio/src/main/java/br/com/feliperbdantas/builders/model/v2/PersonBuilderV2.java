package br.com.feliperbdantas.builders.model.v2;

import br.com.feliperbdantas.builders.model.v1.PersonBuilder;
import br.com.feliperbdantas.model.Person;

public class PersonBuilderV2 {
    private final Person person;

    private PersonBuilderV2(Person person) {
        this.person = (person != null) ? person : new Person();
    }

    public static PersonBuilderV2 create() {
        return new PersonBuilderV2(null);
    }

    public static PersonBuilderV2 from(Person existingPerson) {
        return new PersonBuilderV2(existingPerson);
    }

    public PersonBuilderV2 id(Long id) {
        person.setId(id);

        return this;
    }

    public PersonBuilderV2 firstName(String firstName) {
        person.setFirstName(firstName);

        return this;
    }

    public PersonBuilderV2 lastName(String lastName) {
        person.setLastName(lastName);

        return this;
    }

    public PersonBuilderV2 address(String address) {
        person.setAddress(address);

        return this;
    }

    public PersonBuilderV2 gender(String gender) {
        person.setGender(gender);

        return this;
    }

    /* public PersonBuilder birthDay(String birthDay) {
        person.setBirthDay(birthDay);

        return this;
    } */

    public PersonBuilderV2 withBasicInfo(String firstName, String lastName, String address, String gender) {
        return this.firstName(firstName)
                .lastName(lastName)
                .address(address)
                .gender(gender);
    }

    public Person build() {
        return person;
    }
}
