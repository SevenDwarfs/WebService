package team.sevendwarfs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Screen;

import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {
    // 增 save

    // 删 delete

    // 改 save

    // 查 findOne, findAll

    // 根据名字查询
    List<Screen> findByCinema(Cinema cinema);
    List<Screen> findByShowDateAndShowTime(Date date, String time);
    List<Screen> findByShowDate(Date date);
    List<Screen> findByShowTime(String time);
    List<Screen> findByRoom(String room);
    List<Screen> findByMovieName(String movieName);
    List<Screen> findByLanguage(String language);
    List<Screen> findByPrice(Double price);
    List<Screen> findByCinemaAndAndMovieName(Cinema cinema, String name);
}
