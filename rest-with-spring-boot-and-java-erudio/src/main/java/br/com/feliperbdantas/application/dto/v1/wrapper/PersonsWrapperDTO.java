package br.com.feliperbdantas.application.dto.v1.wrapper;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "persons")
public class PersonsWrapperDTO {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "person")
    private List<PersonDTO> persons;

    public PersonsWrapperDTO() {}

    public PersonsWrapperDTO(List<PersonDTO> persons) {
        this.persons = persons;
    }

    public List<PersonDTO> getPersons() { return persons; }

    public void setPersons(List<PersonDTO> persons) { this.persons = persons; }
}
