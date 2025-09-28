package br.com.feliperbdantas.interfaces.rest.controller.v1;

import br.com.feliperbdantas.application.dto.v1.BookDTO;
import br.com.feliperbdantas.application.dto.v1.wrapper.BooksWrapperDTO;
import br.com.feliperbdantas.application.service.v1.contract.BookService;
import br.com.feliperbdantas.interfaces.rest.controller.v1.contract.BookControllerDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController("bookControllerV1")
@RequestMapping("/api/v1/book")
public class BookControllerImpl implements BookControllerDocs {
    @Autowired
    private BookService service;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<BooksWrapperDTO> findAll() {
        List<BookDTO> books = service.findAll();

        return ResponseEntity.ok(new BooksWrapperDTO(books));
    }

    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO book) {
        BookDTO bookCreated = service.create(book);

        return ResponseEntity
                .created(URI.create("/v1/book/" + bookCreated.getId()))
                .body(bookCreated);
    }

    @PutMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO book) {
        return ResponseEntity.ok(service.update(book));
    }

    @DeleteMapping(
            value = "/{id}"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
