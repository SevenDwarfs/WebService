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
    public void create(Screen screen);

    // 删
    @Transactional
    public void delete(Integer id);
    @Transactional
    public void delete(Screen screen);

    // 改
    @Transactional
    public Screen update(Screen screen);

    // 查
    @Transactional(readOnly = true)
    public Screen findById(Integer id);
    @Transactional(readOnly = true)
    public List<Screen> findByTime(Date time);
    @Transactional(readOnly = true)
    public List<Screen> findByMovie(Movie movie);
    @Transactional(readOnly = true)
    public List<Screen> findByCinemaAndMovie(Cinema cinema, Movie movie);
    @Transactional
    public List<Screen> findAll();
}
