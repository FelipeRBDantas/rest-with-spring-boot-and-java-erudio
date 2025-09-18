package br.com.feliperbdantas.services.v1;

import br.com.feliperbdantas.data.dto.v1.PersonDTO;
import br.com.feliperbdantas.exception.ResourceNotFoundException;
import static br.com.feliperbdantas.mapper.ObjectMapper.parseObject;
import static br.com.feliperbdantas.mapper.ObjectMapper.parseListObjects;

import br.com.feliperbdantas.mapper.custom.v1.PersonMapper;
import br.com.feliperbdantas.model.Person;
import br.com.feliperbdantas.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personServiceV1")
public class PersonService {
    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    public List<PersonDTO> findAll() {
        logger.info("[V1] Finding all People.");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("[V1] Finding one Person.");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("[V1] Creating one Person.");

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("[V1] Updating one Person.");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        /* BeanUtils.copyProperties(person, entity, "id"); */

        var entityFromDTO = converter.updateEntityFromDTO(person, entity);

        return parseObject(repository.save(entityFromDTO), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("[V1] Deleting one Person.");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        repository.delete(entity);
    }
}
