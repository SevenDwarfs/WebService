package team.sevendwarfs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Screen;

import java.util.Date;

/**
 * Created by deng on 2017/6/5.
 */
public interface ScreenRepository extends JpaRepository<Screen, Integer> {
    // 增 save

    // 删 delete

    // 改 save

    // 查 findOne, findAll

    // 根据名字查询
    public Screen findByCinema(Cinema cinema);
    public Screen findByTime(Date time);
    public Screen findByRoom(String room);
    public Screen findByMovieName(String movieName);
    public Screen findByLanguage(String language);
}
