package br.com.feliperbdantas.interfaces.rest.controller.v1;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.application.dto.v1.wrapper.PersonsWrapperDTO;
import br.com.feliperbdantas.application.service.v1.contract.PersonService;
import br.com.feliperbdantas.interfaces.rest.controller.v1.contract.PersonControllerDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController("personControllerV1")
@RequestMapping("/api/v1/person")
public class PersonControllerImpl implements PersonControllerDocs {
    @Autowired
    private PersonService service;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<PersonsWrapperDTO> findAll() {
        List<PersonDTO> persons = service.findAll();

        return ResponseEntity.ok(new PersonsWrapperDTO(persons));
    }

    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
        PersonDTO personCreated = service.create(person);

        return ResponseEntity
                .created(URI.create("/v1/person/" + personCreated.getId()))
                .body(personCreated);
    }

    @PutMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        return ResponseEntity.ok(service.update(person));
    }

    @DeleteMapping(
            value = "/{id}"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
