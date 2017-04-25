package team.sevendwarfs.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.dao.MovieDao;
import team.sevendwarfs.persistence.entities.Movie;

import java.util.List;

/**
 * MovieService 服务类
 * Created by deng on 2017/4/25.
 */
@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieDao dao;

    /**
     * Instantiates a new Movie service.
     */
    public MovieService() {
        super();
    }

    /**
     * Create.
     *
     * @param entity the entity
     */
// API
    public void create(final Movie entity) {
        this.dao.create(entity);
    }

    /**
     * Find one movie.
     *
     * @param id the id
     * @return the movie
     */
    public Movie findOne(final Integer id) {
        return this.dao.findOne(id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Movie> findAll() {
        return this.dao.findAll();
    }

}
