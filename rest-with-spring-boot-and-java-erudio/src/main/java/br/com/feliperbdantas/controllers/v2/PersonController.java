package br.com.feliperbdantas.controllers.v2;

import br.com.feliperbdantas.data.dto.v2.PersonDTO;
import br.com.feliperbdantas.services.v2.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController("personControllerV2")
@RequestMapping("/v2/person")
public class PersonController {
    @Autowired
    private PersonServiceImpl service;

    @GetMapping(
            name = "/v2/person/findAll",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(
            name = "/v2/person/findById",
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(
            name = "/v2/person/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
        PersonDTO personCreated = service.create(person);

        return ResponseEntity
                .created(URI.create("/v2/person/" + personCreated.getId()))
                .body(personCreated);
    }

    @PutMapping(
            name = "/v2/person/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        return ResponseEntity.ok(service.update(person));
    }

    @DeleteMapping(
            name = "/v2/person/delete",
            value = "/{id}"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
