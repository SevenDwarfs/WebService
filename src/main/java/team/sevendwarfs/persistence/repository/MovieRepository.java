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
    public Movie findByChineseName(String chineseName);

    // 根据英文名字查询
    public Movie findByEnglishName(String englishName);

    // 根据类型查询
    public List<Movie> findByType(String type);

    // 根据上映时间查询
    public List<Movie> findByReleaseDate(Date releaseDate);

    // 根据 演员/导演 查询
    public List<Movie> findByMoviersContains(Person person);

    // 查询给定日期之后的电影
    public List<Movie> findByReleaseDateAfter(Date releaseDate);
}
