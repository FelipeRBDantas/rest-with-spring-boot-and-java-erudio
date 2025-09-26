package br.com.feliperbdantas.application.service.v1;

import br.com.feliperbdantas.application.dto.v1.PersonDTO;
import br.com.feliperbdantas.application.presenter.v1.PersonPresenter;
import br.com.feliperbdantas.domain.service.PersonService;
import br.com.feliperbdantas.exception.RequiredObjectIsNullException;
import br.com.feliperbdantas.exception.ResourceNotFoundException;

import br.com.feliperbdantas.application.mapper.PersonMapper;
import br.com.feliperbdantas.domain.model.Person;
import br.com.feliperbdantas.infrastructure.persistence.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("personServiceV1")
public class PersonServiceImpl implements PersonService {
    private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    @Autowired
    PersonPresenter presenter;

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS,
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public List<PersonDTO> findAll() {
        logger.info("[V1] Finding all People.");

        return presenter.present(mapper.toDtoList(repository.findAll()));
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS,
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public PersonDTO findById(Long id) {
        logger.info("[V1] Finding one Person.");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        return presenter.present(mapper.toDto(entity));
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public PersonDTO create(PersonDTO person) {
        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("[V1] Creating one Person.");

        var entityFromDTO = mapper.toEntity(person);

        return presenter.present(mapper.toDto(repository.save(entityFromDTO)));
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ
    )
    @Override
    public PersonDTO update(PersonDTO person) {
        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("[V1] Updating one Person.");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        mapper.updateFromDto(person, entity);

        return presenter.present(mapper.toDto(repository.save(entity)));
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ
    )
    @Override
    public void delete(Long id) {
        logger.info("[V1] Deleting one Person.");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        repository.delete(entity);
    }
}
