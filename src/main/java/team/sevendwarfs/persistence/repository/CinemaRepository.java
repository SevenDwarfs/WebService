package team.sevendwarfs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Screen;

import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    // 增 save

    // 删 delete

    // 改 save

    // 查 findOne, findAll

    // 根据名字查询
    Cinema findByName(String name);
    List<Cinema> findByNameContaining(String name);
    Cinema findByPhone(String phone);
    Cinema findByAddress(String address);
    List<Cinema> findByAddressContaining(String address);
    List<Cinema> findByScreens(Screen screen);
}
