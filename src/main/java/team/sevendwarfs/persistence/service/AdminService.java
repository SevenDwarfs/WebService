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
    public void create(Admin user);

    // 删
    @Transactional
    public void delete(Admin user);
    @Transactional
    public void delete(Integer id);

    // 改
    @Transactional
    public Admin update(Admin user);


    // 查
    @Transactional(readOnly = true)
    public Admin findOne(Integer id);
    @Transactional(readOnly = true)
    public Admin findByName(String name);
    @Transactional(readOnly = true)
    public Admin findByEmail(String email);
    @Transactional(readOnly = true)
    public Admin findByPhone(String phone);
    @Transactional(readOnly = true)
    public List<Admin> findAll();
    @Transactional(readOnly = true)
    public Long count();

    /**
     * 验证成功返回用户实例，失败返回 null
     * @param username 用户名
     * @param password 密码，非密码的MD5
     * @return
     */
    @Transactional(readOnly = true)
    public Admin verify(String adminname, String password);
}
