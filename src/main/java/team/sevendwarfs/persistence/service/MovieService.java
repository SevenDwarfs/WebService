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
    void create(Movie movie);

    // 删
    @Transactional
    void delete(Integer id);
    @Transactional
    void delete(Movie movie);

    // 改
    @Transactional
    Movie update(Movie movie);

    // 查
    @Transactional(readOnly = true)
    Movie findById(Integer id);
    @Transactional(readOnly = true)
    Movie findByChineseName(String name);
    @Transactional(readOnly = true)
    Movie findByEnglishName(String name);
    @Transactional(readOnly = true)
    List<Movie> findByDayDate(Date date);
    @Transactional(readOnly = true)
    List<Movie> findByMonthDate(Date date);
    @Transactional(readOnly = true)
    List<Movie> findByYearDate(Date date);
    @Transactional(readOnly = true)
    List<Movie> findDateAfter(Date date);
    @Transactional(readOnly = true)
    List<Movie> findShowing();
    @Transactional(readOnly = true)
    List<Movie> findByPerson(Person person);
    @Transactional(readOnly = true)
    List<Movie> findAll();

    @Transactional(readOnly = true)
    List<Movie> findByType(String type, int beginIndex, int number);

    @Transactional(readOnly = true)
    List<Movie> findByType(String type, int id);
    @Transactional(readOnly = true)
    List<Movie> findByTypeAndCountry(String type, String country);


    void filterMovieByYear(List<Movie> movieList, int year);

    @Transactional(readOnly = true)
    List<Movie> findByNameContaining(String name);
}
