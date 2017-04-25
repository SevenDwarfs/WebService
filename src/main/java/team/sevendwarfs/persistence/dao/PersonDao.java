package team.sevendwarfs.persistence.dao;

import team.sevendwarfs.persistence.entities.Person;

import java.util.List;

/**
 * Created by deng on 2017/4/25.
 */
public interface PersonDao {

    Person findOne(Integer id);

    List<Person> findAll();

    void create(Person entity);

    Person update(Person entity);

    void delete(Person entity);

    void deleteById(Integer entityId);
}
