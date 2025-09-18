package br.com.feliperbdantas.builders.model.v1;

import br.com.feliperbdantas.model.Person;

public class PersonBuilder {
    private final Person person;

    private PersonBuilder() {
        this.person = new Person();
    }

    public static PersonBuilder create() {
        return new PersonBuilder();
    }

    public PersonBuilder id(Long id) {
        person.setId(id);

        return this;
    }

    public PersonBuilder firstName(String firstName) {
        person.setFirstName(firstName);

        return this;
    }

    public PersonBuilder lastName(String lastName) {
        person.setLastName(lastName);

        return this;
    }

    public PersonBuilder address(String address) {
        person.setAddress(address);

        return this;
    }

    public PersonBuilder gender(String gender) {
        person.setGender(gender);

        return this;
    }

    public PersonBuilder withBasicInfo(String firstName, String lastName, String address, String gender) {
        return this.firstName(firstName)
                .lastName(lastName)
                .address(address)
                .gender(gender);
    }

    public Person build() {
        return person;
    }
}
