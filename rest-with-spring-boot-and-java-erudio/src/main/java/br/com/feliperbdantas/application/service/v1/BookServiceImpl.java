package br.com.feliperbdantas.application.service.v1;

import br.com.feliperbdantas.application.dto.v1.BookDTO;
import br.com.feliperbdantas.application.mapper.BookMapper;
import br.com.feliperbdantas.application.presenter.v1.BookPresenter;
import br.com.feliperbdantas.application.service.v1.contract.BookService;
import br.com.feliperbdantas.domain.model.Book;
import br.com.feliperbdantas.infrastructure.persistence.repository.BookRepository;
import br.com.feliperbdantas.interfaces.exception.RequiredObjectIsNullException;
import br.com.feliperbdantas.interfaces.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookServiceV1")
public class BookServiceImpl implements BookService {
    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class.getName());

    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper mapper;

    @Autowired
    BookPresenter presenter;

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS,
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public List<BookDTO> findAll() {
        logger.info("[V1] Finding all Books.");

        return presenter.present(mapper.toDtoList(repository.findAll()));
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS,
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public BookDTO findById(Long id) {
        logger.info("[V1] Finding one Book.");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        return presenter.present(mapper.toDto(entity));
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public BookDTO create(BookDTO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("[V1] Creating one Book.");

        var entityFromDTO = mapper.toEntity(book);

        return presenter.present(mapper.toDto(repository.save(entityFromDTO)));
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ
    )
    @Override
    public BookDTO update(BookDTO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("[V1] Updating one Book.");

        Book entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        mapper.updateFromDto(book, entity);

        return presenter.present(mapper.toDto(repository.save(entity)));
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ
    )
    @Override
    public void delete(Long id) {
        logger.info("[V1] Deleting one Book.");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        repository.delete(entity);
    }
}
