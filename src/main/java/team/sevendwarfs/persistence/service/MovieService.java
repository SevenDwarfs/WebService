package team.sevendwarfs.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;
import team.sevendwarfs.persistence.repository.MovieRepository;

import java.util.Date;
import java.util.List;

/**
 * MovieService 服务类
 * Created by deng on 2017/4/25.
 */
@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public MovieService() {
        super();
    }

    public Movie create(final Movie entity) {
        return this.movieRepository.save(entity);
    }

    public void deleteById(Integer id) {
        this.movieRepository.delete(id);
    }

    public void delete(Movie movie) {
        this.movieRepository.delete(movie);
    }

    public Movie update(Movie movie) {
        return this.movieRepository.save(movie);
    }

    public Long count() {
        return this.movieRepository.count();
    }

    public Movie findOne(final Integer id) {
        return this.movieRepository.findOne(id);
    }

    public List<Movie> findAll() {
        return this.movieRepository.findAll();
    }

    // 根据中文名字查询
    public List<Movie> findByChineseName(String chineseName) {
        return this.movieRepository.findByChineseName(chineseName);
    }

    // 根据英文名字查询
    public List<Movie> findByEnglishName(String englishName) {
        return this.movieRepository.findByEnglishName(englishName);
    }

    // 根据类型查询
    public List<Movie> findByType(String type) {
        return this.movieRepository.findByType(type);
    }

    // 根据上映时间查询
    public List<Movie> findByReleaseDate(Date releaseDate) {
        return this.movieRepository.findByReleaseDate(releaseDate);
    }

    // 根据 演员/导演 查询
    public List<Movie> findByMoviersContains(Person person) {
        return this.movieRepository.findByMoviersContains(person);
    }
}
