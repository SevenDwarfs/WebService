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
    public List<Screen> findByCinema(Cinema cinema);
    public List<Screen> findByShowDateAndShowTime(Date date, String time);
    public List<Screen> findByShowDate(Date date);
    public List<Screen> findByShowTime(String time);
    public List<Screen> findByRoom(String room);
    public List<Screen> findByMovieName(String movieName);
    public List<Screen> findByLanguage(String language);
    public List<Screen> findByPrice(Double price);
    public List<Screen> findByCinemaAndAndMovieName(Cinema cinema, String name);
}
