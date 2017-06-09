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
    public void create(Cinema cinema);

    // 删
    @Transactional
    public void delete(Integer id);
    @Transactional
    public void delete(Cinema cinema);

    // 改
    @Transactional
    public Cinema update(Cinema cinema);

    // 查
    @Transactional(readOnly = true)
    public Cinema findById(Integer id);
    @Transactional(readOnly = true)
    public Cinema findByName(String name);
    @Transactional(readOnly = true)
    public List<Cinema> findByAddress(String address);
    @Transactional(readOnly = true)
    public List<Cinema> findByMovie(Movie movie);
    @Transactional(readOnly = true)
    public Cinema findByPhone(String phone);
    @Transactional
    public List<Cinema> findAll();
}
