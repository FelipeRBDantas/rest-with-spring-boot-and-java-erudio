package br.com.feliperbdantas.application.service.v1.contract;

import br.com.feliperbdantas.application.dto.v1.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> findAll();
    BookDTO findById(Long id);
    BookDTO create(BookDTO book);
    BookDTO update(BookDTO book);
    void delete(Long id);
}
