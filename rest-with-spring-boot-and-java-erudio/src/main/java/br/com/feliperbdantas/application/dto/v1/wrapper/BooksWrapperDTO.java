package br.com.feliperbdantas.application.dto.v1.wrapper;

import br.com.feliperbdantas.application.dto.v1.BookDTO;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "books")
public class BooksWrapperDTO {
    @JacksonXmlProperty(localName = "book")
    private List<BookDTO> books;

    public BooksWrapperDTO() {}

    public BooksWrapperDTO(List<BookDTO> books) {
        this.books = books;
    }

    public List<BookDTO> getBooks() { return books; }

    public void setBooks(List<BookDTO> books) { this.books = books; }
}
