package team.sevendwarfs.persistence.service;

import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;

import java.util.List;

/**
 * Created by deng on 2017/4/28.
 */
public interface PersonService {
    // 增
    @Transactional
    void create(Person person);

    // 删
    @Transactional
    void delete(Integer id);
    @Transactional
    void delete(Person person);

    // 改
    @Transactional
    Person update(Person person);

    // 查
    @Transactional(readOnly = true)
    Person findById(Integer id);
    @Transactional(readOnly = true)
    List<Person> findByName(String name);
    @Transactional(readOnly = true)
    List<Person> findByType(String type);
    @Transactional(readOnly = true)
    List<Person> findByMovie(Movie movie);
    @Transactional
    List<Person> findAll();
}
