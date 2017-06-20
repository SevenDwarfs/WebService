package team.sevendwarfs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Person;

import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/4/27.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // 增 save

    // 删 delete

    // 改 save

    // 查 findOne, findAll

    // 根据中文名字查询
    Movie findByChineseName(String chineseName);

    // 根据英文名字查询
    Movie findByEnglishName(String englishName);

    // 根据类型查询
    List<Movie> findByType(String type);

    // 根据上映时间查询
    List<Movie> findByReleaseDate(Date releaseDate);

    // 根据 演员/导演 查询
    List<Movie> findByMoviersContains(Person person);

    // 查询给定日期之后的电影
    List<Movie> findByReleaseDateAfter(Date releaseDate);

    // 查询日期之间的电影
    List<Movie> findByReleaseDateBetween(Date date1, Date date2);

    List<Movie> findByTypeAndCountry(String type, String country);
    List<Movie> findByTypeAndCountryContaining(String type, String country);
    List<Movie> findByCountry(String country);

    List<Movie> findByChineseNameContaining(String name);
    List<Movie> findByEnglishNameContaining(String name);
}
