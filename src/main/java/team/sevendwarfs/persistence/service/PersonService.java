package team.sevendwarfs.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.dao.PersonDao;
import team.sevendwarfs.persistence.entities.Person;

import java.util.List;

/**
 * Created by deng on 2017/4/25.
 */
@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonDao dao;

    /**
     * Instantiates a new Person service.
     */
    public PersonService() {
        super();
    }

    /**
     * Create.
     *
     * @param entity the entity
     */
// API
    public void create(final Person entity) {
        this.dao.create(entity);
    }

    /**
     * Find one person.
     *
     * @param id the id
     * @return the person
     */
    public Person findOne(final Integer id) {
        return this.dao.findOne(id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Person> findAll() {
        return this.dao.findAll();
    }

}
