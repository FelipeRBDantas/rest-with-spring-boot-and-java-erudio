package br.com.feliperbdantas.services.v1.impl;

import br.com.feliperbdantas.data.dto.v1.PersonDTO;
import br.com.feliperbdantas.exception.ResourceNotFoundException;
import static br.com.feliperbdantas.mappers.ObjectMapper.parseObject;
import static br.com.feliperbdantas.mappers.ObjectMapper.parseListObjects;

import br.com.feliperbdantas.mappers.custom.v1.PersonMapper;
import br.com.feliperbdantas.models.Person;
import br.com.feliperbdantas.repository.PersonRepository;
import br.com.feliperbdantas.services.v1.PersonService;
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
    PersonMapper converter;

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS,
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public List<PersonDTO> findAll() {
        logger.info("[V1] Finding all People.");

        return parseListObjects(repository.findAll(), PersonDTO.class);
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

        return parseObject(entity, PersonDTO.class);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED
    )
    @Override
    public PersonDTO create(PersonDTO person) {
        logger.info("[V1] Creating one Person.");

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ
    )
    @Override
    public PersonDTO update(PersonDTO person) {
        logger.info("[V1] Updating one Person.");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        /* BeanUtils.copyProperties(person, entity, "id"); */

        var entityFromDTO = converter.updateEntityFromDTO(person, entity);

        return parseObject(repository.save(entityFromDTO), PersonDTO.class);
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
