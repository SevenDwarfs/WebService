package team.sevendwarfs.persistence.service;



import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Screen;

import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */
public interface ScreenService {
    // 增
    @Transactional
    void create(Screen screen);

    // 删
    @Transactional
    void delete(Integer id);
    @Transactional
    void delete(Screen screen);

    // 改
    @Transactional
    Screen update(Screen screen);

    // 查
    @Transactional(readOnly = true)
    Screen findById(Integer id);
    @Transactional(readOnly = true)
    List<Screen> findByDate(Date time);
    @Transactional(readOnly = true)
    List<Screen> findByTime(String time);
    @Transactional(readOnly = true)
    List<Screen> findByMovie(Movie movie);
    @Transactional(readOnly = true)
    List<Screen> findByCinemaAndMovie(Cinema cinema, Movie movie);
    @Transactional
    List<Screen> findAll();
}
