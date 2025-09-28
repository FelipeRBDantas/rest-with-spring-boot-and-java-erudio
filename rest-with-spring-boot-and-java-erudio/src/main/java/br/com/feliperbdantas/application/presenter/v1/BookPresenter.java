package br.com.feliperbdantas.application.presenter.v1;

import br.com.feliperbdantas.application.dto.v1.BookDTO;

import java.util.List;

public interface BookPresenter {
    BookDTO present(BookDTO dto);
    List<BookDTO> present(List<BookDTO> dtos);
}
