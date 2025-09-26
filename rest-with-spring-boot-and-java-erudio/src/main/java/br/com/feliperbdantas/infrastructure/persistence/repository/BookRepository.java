package br.com.feliperbdantas.infrastructure.persistence.repository;

import br.com.feliperbdantas.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
