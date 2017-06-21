package team.sevendwarfs.persistence.service;

import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.entities.FilmOrder;
import team.sevendwarfs.persistence.entities.Screen;
import team.sevendwarfs.persistence.entities.User;

import java.util.List;

/**
 * Created by deng on 2017/6/21.
 */

public interface FilmOrderService {
    // 增
    @Transactional
    void create(FilmOrder filmOrder);

    // 删
    @Transactional
    void delete(FilmOrder filmOrder);
    @Transactional
    void delete(Integer id);

    // 改
    @Transactional
    FilmOrder update(FilmOrder filmOrder);

    // 查
    @Transactional(readOnly = true)
    FilmOrder findById(Integer id);
    @Transactional(readOnly = true)
    List<FilmOrder> findByUser(User user);
    @Transactional(readOnly = true)
    List<FilmOrder> findByScreen(Screen screen);
}
