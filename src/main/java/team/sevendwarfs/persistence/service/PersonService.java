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
    public void create(Person person);

    // 删
    @Transactional
    public void delete(Integer id);
    @Transactional
    public void delete(Person person);

    // 改
    @Transactional
    public Person update(Person person);

    // 查
    @Transactional(readOnly = true)
    public Person findById(Integer id);
    @Transactional(readOnly = true)
    public Person findByName(String name);
    @Transactional(readOnly = true)
    public List<Person> findByType(String type);
    @Transactional(readOnly = true)
    public List<Person> findByMovie(Movie movie);
    @Transactional
    public List<Person> findAll();
}
