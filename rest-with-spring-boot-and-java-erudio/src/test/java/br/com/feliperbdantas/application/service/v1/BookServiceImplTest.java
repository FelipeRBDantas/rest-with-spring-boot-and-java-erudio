package br.com.feliperbdantas.application.service.v1;

import br.com.feliperbdantas.application.dto.v1.BookDTO;
import br.com.feliperbdantas.application.mapper.BookMapper;
import br.com.feliperbdantas.application.mocks.MockBook;
import br.com.feliperbdantas.application.presenter.v1.BookPresenter;
import br.com.feliperbdantas.domain.model.Book;
import br.com.feliperbdantas.infrastructure.persistence.repository.BookRepository;
import br.com.feliperbdantas.interfaces.exception.RequiredObjectIsNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {
    MockBook input;

    @Mock
    BookRepository repository;

    @Mock
    BookMapper mapper;

    @Mock
    BookPresenter presenter;

    @InjectMocks
    BookServiceImpl service;

    @BeforeEach
    void setUp() {
        input = new MockBook();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Book> books = input.mockEntityList();

        List<BookDTO> booksDTO = input.mockDTOList();

        String pathRoot = "http://localhost:8080";

        List<BookDTO> booksDTOWithHateoas = input.mockDTOList()
                .stream()
                .peek(bookDTOWithHateoas -> {
                            bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book/" + bookDTOWithHateoas.getId())).withSelfRel());
                            bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("findAll"));
                            bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("create"));
                            bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("update"));
                            bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book/" + bookDTOWithHateoas.getId())).withRel("delete"));
                })
                .toList();

        when(repository.findAll()).thenReturn(books);

        when(mapper.toDtoList(books)).thenReturn(booksDTO);

        when(presenter.present(booksDTO)).thenReturn(booksDTOWithHateoas);

        var result = service.findAll();

        assertNotNull(result);
        assertEquals(14, result.size());

        var resultBookOne = result.get(1);

        assertNotNull(resultBookOne.getId());
        assertNotNull(resultBookOne.getLinks());

        assertTrue(
                resultBookOne.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                                && link.getHref().endsWith("/api/v1/book/1"))
        );

        assertTrue(
                resultBookOne.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                resultBookOne.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                resultBookOne.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                resultBookOne.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/v1/book/1"))
        );

        assertEquals("Some Author1", resultBookOne.getAuthor());
        assertEquals(25D, resultBookOne.getPrice());
        assertEquals("Some Title1", resultBookOne.getTitle());
        assertNotNull(resultBookOne.getLaunchDate());

        var resultBookTwo = result.get(2);

        assertNotNull(resultBookTwo.getId());
        assertNotNull(resultBookTwo.getLinks());

        assertTrue(
                resultBookTwo.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                                && link.getHref().endsWith("/api/v1/book/2"))
        );

        assertTrue(
                resultBookTwo.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                resultBookTwo.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                resultBookTwo.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                resultBookTwo.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/v1/book/2"))
        );

        assertEquals("Some Author2", resultBookTwo.getAuthor());
        assertEquals(25D, resultBookTwo.getPrice());
        assertEquals("Some Title2", resultBookTwo.getTitle());
        assertNotNull(resultBookTwo.getLaunchDate());
    }

    @Test
    void findById() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        BookDTO bookDTO = input.mockDTO(1);

        String pathRoot = "http://localhost:8080";

        BookDTO bookDTOWithHateoas = input.mockDTO(1);
        bookDTOWithHateoas.setId(1L);
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book/1")).withSelfRel());
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("findAll"));
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("create"));
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("update"));
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book/1")).withRel("delete"));

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        when(mapper.toDto(book)).thenReturn(bookDTO);

        when(presenter.present(bookDTO)).thenReturn(bookDTOWithHateoas);

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/v1/book/1"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/v1/book/1"))
        );

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void create() {
        Book book = input.mockEntity(1);

        Book persisted = book;
        persisted.setId(1L);

        BookDTO bookDTO = input.mockDTO(1);

        String pathRoot = "http://localhost:8080";

        BookDTO bookDTOWithHateoas = input.mockDTO(1);
        bookDTOWithHateoas.setId(1L);
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book/1")).withSelfRel());
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("findAll"));
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("create"));
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("update"));
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book/1")).withRel("delete"));

        when(mapper.toEntity(bookDTO)).thenReturn(book);

        when(repository.save(book)).thenReturn(persisted);

        when(mapper.toDto(book)).thenReturn(bookDTO);

        when(presenter.present(bookDTO)).thenReturn(bookDTOWithHateoas);

        var result = service.create(bookDTO);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                                && link.getHref().endsWith("/api/v1/book/1"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/v1/book/1"))
        );

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testCreateWithNullBook() {
        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> {
                    service.create(null);
                });

        String expectMessage = "It is not allowed to persist a null object.";

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectMessage));
    }

    @Test
    void update() {
        Book book = input.mockEntity(1);

        Book persisted = book;
        persisted.setId(1L);

        BookDTO bookDTO = input.mockDTO(1);

        String pathRoot = "http://localhost:8080";

        BookDTO bookDTOWithHateoas = input.mockDTO(1);
        bookDTOWithHateoas.setId(1L);
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book/1")).withSelfRel());
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("findAll"));
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("create"));
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book")).withRel("update"));
        bookDTOWithHateoas.add(Link.of(pathRoot.concat("/api/v1/book/1")).withRel("delete"));

        when(mapper.toEntity(bookDTO)).thenReturn(book);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        when(repository.save(book)).thenReturn(persisted);

        when(mapper.toDto(book)).thenReturn(bookDTO);

        when(presenter.present(bookDTO)).thenReturn(bookDTOWithHateoas);

        var result = service.update(bookDTO);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                                && link.getHref().endsWith("/api/v1/book/1"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                                && link.getHref().endsWith("/api/v1/book"))
        );

        assertTrue(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/v1/book/1"))
        );

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testUpdateWithNullBook() {
        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> {
                    service.update(null);
                });

        String expectMessage = "It is not allowed to persist a null object.";

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectMessage));
    }

    @Test
    void delete() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        service.delete(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Book.class));

        verifyNoMoreInteractions(repository);
    }
}