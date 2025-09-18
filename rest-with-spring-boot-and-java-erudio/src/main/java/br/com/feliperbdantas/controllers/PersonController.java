package br.com.feliperbdantas.controllers;

import br.com.feliperbdantas.data.dto.v1.PersonDTO;
import br.com.feliperbdantas.data.dto.v2.PersonDTOV2;
import br.com.feliperbdantas.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServices service;

    @GetMapping(
            name = "/person/v1/findAll",
            value = "/v1",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(
            name = "/person/v1/findById",
            value = "v1/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(
            name = "/person/v1/create",
            value = "/v1",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }
    @PostMapping(
            name = "/person/v2/create",
            value = "/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person) {
        return service.createV2(person);
    }

    @PutMapping(
            name = "/person/v1/update",
            value = "/v1",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

    @DeleteMapping(
            name = "/person/v1/delete",
            value = "/v1/{id}"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
