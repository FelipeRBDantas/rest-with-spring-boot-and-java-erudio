package br.com.feliperbdantas.infrastructure.persistence.repository;

import br.com.feliperbdantas.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
