package team.sevendwarfs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.sevendwarfs.persistence.entities.FilmOrder;
import team.sevendwarfs.persistence.entities.Screen;
import team.sevendwarfs.persistence.entities.User;

import java.util.List;

/**
 * Created by deng on 2017/6/21.
 */

@Repository
public interface FilmOrderRepository extends JpaRepository<FilmOrder, Integer> {
    // 增 save

    // 删 delete

    // 改 save

    // 查 findOne, findAll

    // 根据名字查询
    List<FilmOrder> findByUser(User user);
    List<FilmOrder> findByScreenId(Integer screenId);
}
