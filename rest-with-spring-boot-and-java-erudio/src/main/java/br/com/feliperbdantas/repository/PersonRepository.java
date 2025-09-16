package br.com.feliperbdantas.repository;

import br.com.feliperbdantas.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
