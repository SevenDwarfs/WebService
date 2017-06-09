package team.sevendwarfs.persistence.service;

import org.springframework.transaction.annotation.Transactional;
import team.sevendwarfs.persistence.entities.Admin;

import java.util.List;

/**
 * Created by deng on 2017/6/3.
 */
public interface AdminService {
    // 增
    @Transactional
    void create(Admin user);

    // 删
    @Transactional
    void delete(Admin user);
    @Transactional
    void delete(Integer id);

    // 改
    @Transactional
    Admin update(Admin user);


    // 查
    @Transactional(readOnly = true)
    Admin findOne(Integer id);
    @Transactional(readOnly = true)
    Admin findByName(String name);
    @Transactional(readOnly = true)
    Admin findByEmail(String email);
    @Transactional(readOnly = true)
    Admin findByPhone(String phone);
    @Transactional(readOnly = true)
    List<Admin> findAll();
    @Transactional(readOnly = true)
    Long count();

    /**
     * 验证成功返回用户实例，失败返回 null
     * @param adminname 用户名
     * @param password 密码，非密码的MD5
     * @return
     */
    @Transactional(readOnly = true)
    Admin verify(String adminname, String password);
}
