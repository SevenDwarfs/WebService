package team.sevendwarfs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sevendwarfs.persistence.entities.Admin;

/**
 * Created by deng on 2017/6/3.
 */
public interface AdminRepository extends JpaRepository<Admin, Integer>  {
    // 增 save

    // 删 delete

    // 改 save

    // 查 findOne, findAll

    // 根据用户名查询
    Admin findByAdminName(String userName);

    // 根据邮箱查询
    Admin findByEmail(String email);

    // 根据电话查询
    Admin findByPhone(String phone);

    // 查询用户个数
    long count();
}
