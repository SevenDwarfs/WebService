package team.sevendwarfs.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sevendwarfs.common.Constant;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;
import team.sevendwarfs.persistence.repository.MovieRepository;

import java.util.*;

/**
 * MovieService 服务类
 * Created by deng on 2017/4/25.
 */
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void create(Movie movie) {
        this.movieRepository.save(movie);
    }

    @Override
    public void delete(Integer id) {
        this.movieRepository.delete(id);
    }

    @Override
    public void delete(Movie movie) {
        this.movieRepository.delete(movie);
    }

    @Override
    public Movie update(Movie movie) {
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie findById(Integer id) {
        return this.movieRepository.findOne(id);
    }

    @Override
    public Movie findByChineseName(String name) {
        return this.movieRepository.findByChineseName(name);
    }

    @Override
    public Movie findByEnglishName(String name) {
        return this.movieRepository.findByEnglishName(name);
    }

    @Override
    public List<Movie> findByDayDate(Date date) {
        return this.movieRepository.findByReleaseDate(date);
    }

    @Override
    public List<Movie> findByMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        Date nextMonth = calendar.getTime();
        return this.movieRepository.findByReleaseDateBetween(date, nextMonth);
    }

    @Override
    public List<Movie> findByYearDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        Date nextYear = calendar.getTime();
        return this.movieRepository.findByReleaseDateBetween(date, nextYear);
    }

    @Override
    public List<Movie> findDateAfter(Date date) {
        return this.movieRepository.findByReleaseDateAfter(date);
    }

    @Override
    public List<Movie> findByTypeAndCountry(String type, String country) {
        if (!"all".equals(type)&&!"all".equals(country)) {
            return this.movieRepository.findByTypeAndCountryContaining(type, country);
        } else if ("all".equals(type) && "all".equals(country)) {
            return this.movieRepository.findAll();
        } else if ("all".equals(type)) {
            return this.movieRepository.findByCountry(country);
        } else  {
            return this.movieRepository.findByType(type);
        }
    }

    @Override
    public List<Movie> findShowing() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);

        return this.movieRepository.findByReleaseDateBetween(calendar.getTime
                (), date);
    }

    @Override
    public List<Movie> findByPerson(Person person) {
        return this.movieRepository.findByMoviersContains(person);
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = this.movieRepository.findAll();
//        for (Movie movie : movies) { movie.getMoviers(); }
        return movies;
    }

    @Override
    public List<Movie> findByType(String type, int beginIndex, int number) {
        List<Movie> movies = movieRepository.findByType(type);
        movies.sort(new Comparator<Movie>() {
                        @Override
                        public int compare(Movie o1, Movie o2) {
                            return o1.getId() - o2.getId();
                        }
                    }
        );

        int size = movies.size();
        if (size == 0) { return movies; }

        int endIndex = beginIndex + number;
        if (beginIndex >= size) { beginIndex = size - 1; }
        if (endIndex >= size) { endIndex = size - 1; }

        return movies.subList(beginIndex, endIndex);
    }

    @Override
    public List<Movie> findByType(String type, int id) {
        return findByType(type, id, Constant.searchMovieTypeNumber);
    }

    @Override
    public void filterMovieByYear(List<Movie> movieList, int year) {
        if (year == 0) return;

        Iterator<Movie> it = movieList.iterator();
        Calendar calendar = Calendar.getInstance();
        while (it.hasNext()) {
            Movie movie = it.next();
            if (movie.getReleaseDate() == null) { it.remove(); continue; }
            calendar.setTime(movie.getReleaseDate());
            if (calendar.get(Calendar.YEAR) != year) {it.remove(); continue; }
        }
    }

    @Override
    public List<Movie> findByNameContaining(String name) {
        List<Movie> movieList1 = movieRepository.findByChineseNameContaining
                (name);
        List<Movie> movieList2 = movieRepository.findByEnglishNameContaining
                (name);
        movieList1.addAll(movieList2);
        return movieList1;
    }
}
