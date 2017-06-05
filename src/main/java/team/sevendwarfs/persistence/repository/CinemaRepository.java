package team.sevendwarfs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sevendwarfs.persistence.entities.Cinema;

import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    // 增 save

    // 删 delete

    // 改 save

    // 查 findOne, findAll

    // 根据名字查询
    public Cinema findByName(String name);
    public Cinema findByNameContaining(String name);
    public Cinema findByPhone(String phone);
    public Cinema findByAddress(String address);
    public Cinema findByAddressContaining(String address);

}
