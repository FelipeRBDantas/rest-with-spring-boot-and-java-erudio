package br.com.feliperbdantas.services.v2;

import br.com.feliperbdantas.data.dto.v2.PersonDTO;
import br.com.feliperbdantas.exception.ResourceNotFoundException;
import br.com.feliperbdantas.mapper.custom.v2.PersonMapper;
import br.com.feliperbdantas.model.Person;
import br.com.feliperbdantas.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.feliperbdantas.mapper.ObjectMapper.parseListObjects;
import static br.com.feliperbdantas.mapper.ObjectMapper.parseObject;

@Service("personServiceV2")
public class PersonService {
    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    public List<PersonDTO> findAll() {
        logger.info("[V2] Finding all People.");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("[V2] Finding one Person.");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("[V2] Creating one Person V2.");

        var entity = parseObject(person, Person.class);

        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("[V2] Updating one Person.");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        /* BeanUtils.copyProperties(person, entity, "id"); */

        var entityFromDTO = converter.updateEntityFromDTO(person, entity);

        return parseObject(repository.save(entityFromDTO), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("[V2] Deleting one Person.");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID."));

        repository.delete(entity);
    }
}
