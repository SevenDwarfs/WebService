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
    public Cinema findByName(String name);
    public List<Cinema> findByNameContaining(String name);
    public Cinema findByPhone(String phone);
    public Cinema findByAddress(String address);
    public List<Cinema> findByAddressContaining(String address);
    public List<Cinema> findByScreens(Screen screen);
}
