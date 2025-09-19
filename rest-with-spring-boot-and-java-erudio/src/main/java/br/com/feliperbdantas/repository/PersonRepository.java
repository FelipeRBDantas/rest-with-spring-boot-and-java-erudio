package br.com.feliperbdantas.repository;

import br.com.feliperbdantas.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
