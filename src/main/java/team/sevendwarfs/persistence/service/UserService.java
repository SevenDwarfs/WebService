package team.sevendwarfs.persistence.service;

import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.entities.User;

import java.util.List;

/**
 * Created by deng on 2017/4/28.
 */
public interface UserService {
    // 增
    @Transactional
    void create(User user);

    // 删
    @Transactional
    void delete(User user);
    @Transactional
    void delete(Integer id);

    // 改
    @Transactional
    User update(User user);


    // 查
    @Transactional(readOnly = true)
    User findOne(Integer id);
    @Transactional(readOnly = true)
    User findByName(String name);
    @Transactional(readOnly = true)
    User findByEmail(String email);
    @Transactional(readOnly = true)
    User findByPhone(String phone);
    @Transactional(readOnly = true)
    List<User> findAll();
    @Transactional(readOnly = true)
    Long count();

    /**
     * 验证成功返回用户实例，失败返回 null
     * @param username 用户名
     * @param password 密码，非密码的MD5
     * @return
     */
    @Transactional(readOnly = true)
    User verify(String username, String password);
}
