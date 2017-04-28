package team.sevendwarfs.persistence.service;

import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;

import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/4/28.
 */
public interface MovieService {
    // 增
    @Transactional
    public void create(Movie movie);

    // 删
    @Transactional
    public void delete(Integer id);
    @Transactional
    public void delete(Movie movie);

    // 改
    @Transactional
    public Movie update(Movie movie);

    // 查
    @Transactional(readOnly = true)
    public Movie findById(Integer id);
    @Transactional(readOnly = true)
    public Movie findByChineseName(String name);
    @Transactional(readOnly = true)
    public Movie findByEnglishName(String name);
    @Transactional(readOnly = true)
    public List<Movie> findByDate(Date date);
    @Transactional(readOnly = true)
    public List<Movie> findDateAfter(Date date);
    @Transactional(readOnly = true)
    public List<Movie> findShowing();
    @Transactional(readOnly = true)
    public List<Movie> findByPerson(Person person);
    @Transactional(readOnly = true)
    public List<Movie> findAll();
}
