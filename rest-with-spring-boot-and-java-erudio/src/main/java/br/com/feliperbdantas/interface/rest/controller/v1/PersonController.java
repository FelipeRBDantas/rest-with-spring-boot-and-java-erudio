package br.com.feliperbdantas.controller.v1;

import br.com.feliperbdantas.application.assembler.PersonModelAssembler;
import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController("personControllerV1")
@RequestMapping("/v1/person")
public class PersonController {
    @Autowired
    private PersonService service;

    @Autowired
    private PersonModelAssembler assembler;

    @GetMapping(
            name = "/v1/person/findAll",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<List<EntityModel<PersonDTO>>> findAll() {
        return ResponseEntity.ok(assembler.toFlatList(service.findAll()));
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
    public ResponseEntity<EntityModel<PersonDTO>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(assembler.toModel(service.findById(id)));
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
        PersonDTO personCreated = assembler
                .toModel(service.create(person))
                .getContent();

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
    public ResponseEntity<EntityModel<PersonDTO>> update(@RequestBody PersonDTO person) {
        return ResponseEntity.ok(assembler.toModel(service.update(person)));
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
