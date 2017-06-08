package team.sevendwarfs.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Screen;
import team.sevendwarfs.persistence.repository.ScreenRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */

@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    ScreenRepository screenRepository;

    @Override
    public void create(Screen screen) {
        screenRepository.save(screen);
    }

    @Override
    public void delete(Integer id) {
        screenRepository.delete(id);
    }

    @Override
    public void delete(Screen screen) {
        screenRepository.delete(screen);
    }

    @Override
    public Screen update(Screen screen) {
        return screenRepository.save(screen);
    }

    @Override
    public Screen findById(Integer id) {
        return screenRepository.findOne(id);
    }

    @Override
    public List<Screen> findByTime(String time) {
        return screenRepository.findByShowTime(time);
    }

    @Override
    public List<Screen> findByDate(Date time) {
        return screenRepository.findByShowDate(time);
    }

    @Override
    public List<Screen> findByMovie(Movie movie) {
        List<Screen> screens = screenRepository.findByMovieName(movie
                .getChineseName());

        screens.addAll(screenRepository.findByMovieName(
                                            movie.getEnglishName())
                        );
        return screens;
    }

    @Override
    public List<Screen> findByCinemaAndMovie(Cinema cinema, Movie movie) {
        List<Screen> screens = screenRepository.findByCinemaAndAndMovieName
                (cinema, movie.getChineseName());
        List<Screen> screens1 = screenRepository.findByCinemaAndAndMovieName
                (cinema, movie.getEnglishName());

        screens.addAll(screens1);
        return screens;
    }

    @Override
    public List<Screen> findAll() {
        return screenRepository.findAll();
    }
}
