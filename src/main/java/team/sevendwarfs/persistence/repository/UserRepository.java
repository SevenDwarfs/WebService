package team.sevendwarfs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.sevendwarfs.persistence.entities.User;

/**
 * Created by deng on 2017/4/27.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 增 save

    // 删 delete

    // 改 save

    // 查 findOne, findAll

    // 根据用户名查询
    User findByUserName(String userName);

    // 根据邮箱查询
    User findByEmail(String email);

    // 根据电话查询
    User findByPhone(String phone);

    // 查询用户个数
    long count();

}
