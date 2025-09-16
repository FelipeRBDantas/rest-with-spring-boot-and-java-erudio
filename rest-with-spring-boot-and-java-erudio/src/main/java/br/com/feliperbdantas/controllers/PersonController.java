package br.com.feliperbdantas.controllers;

import br.com.feliperbdantas.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    private PersonServices personServices;
}
