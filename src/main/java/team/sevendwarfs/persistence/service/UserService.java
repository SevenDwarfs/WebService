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
    public void create(User user);

    // 删
    @Transactional
    public void delete(User user);
    @Transactional
    public void delete(Integer id);

    // 改
    @Transactional
    public User update(User user);


    // 查
    @Transactional(readOnly = true)
    public User findOne(Integer id);
    @Transactional(readOnly = true)
    public User findByName(String name);
    @Transactional(readOnly = true)
    public User findByEmail(String email);
    @Transactional(readOnly = true)
    public User findByPhone(String phone);
    @Transactional(readOnly = true)
    public List<User> findAll();
    @Transactional(readOnly = true)
    public Long count();

    /**
     * 验证成功返回用户实例，失败返回 null
     * @param username 用户名
     * @param password 密码，非密码的MD5
     * @return
     */
    @Transactional(readOnly = true)
    public User verify(String username, String password);
}
