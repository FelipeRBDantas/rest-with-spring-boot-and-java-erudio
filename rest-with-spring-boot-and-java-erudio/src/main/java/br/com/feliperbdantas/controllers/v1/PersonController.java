package br.com.feliperbdantas.controllers.v1;

import br.com.feliperbdantas.data.dto.v1.PersonDTO;
import br.com.feliperbdantas.services.v1.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController("personControllerV1")
@RequestMapping("/v1/person")
public class PersonController {
    @Autowired
    private PersonServiceImpl service;

    @GetMapping(
            name = "/v1/person/findAll",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(
            name = "/v1/person/findById",
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
            name = "/v1/person/create",
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
            name = "/v1/person/update",
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
            name = "/v1/person/delete",
            value = "/{id}"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
