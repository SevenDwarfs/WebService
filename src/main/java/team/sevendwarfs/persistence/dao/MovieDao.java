package team.sevendwarfs.persistence.dao;

import team.sevendwarfs.persistence.entities.Movie;

import java.util.List;

/**
 * Created by deng on 2017/4/25.
 */
public interface MovieDao {

    Movie findOne(Integer id);

    List<Movie> findAll();

    void create(Movie entity);

    Movie update(Movie entity);

    void delete(Movie entity);

    void deleteById(Integer entityId);
}
