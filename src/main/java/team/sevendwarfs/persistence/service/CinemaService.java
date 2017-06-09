package team.sevendwarfs.persistence.service;

import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Movie;

import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */
public interface CinemaService {
    // 增
    @Transactional
    void create(Cinema cinema);

    // 删
    @Transactional
    void delete(Integer id);
    @Transactional
    void delete(Cinema cinema);

    // 改
    @Transactional
    Cinema update(Cinema cinema);

    // 查
    @Transactional(readOnly = true)
    Cinema findById(Integer id);
    @Transactional(readOnly = true)
    Cinema findByName(String name);
    @Transactional(readOnly = true)
    List<Cinema> findByAddress(String address);
    @Transactional(readOnly = true)
    List<Cinema> findByMovie(Movie movie);
    @Transactional(readOnly = true)
    Cinema findByPhone(String phone);
    @Transactional
    List<Cinema> findAll();
}
