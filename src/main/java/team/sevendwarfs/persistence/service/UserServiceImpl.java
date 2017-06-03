package team.sevendwarfs.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.User;
import team.sevendwarfs.persistence.repository.UserRepository;

import java.util.List;

/**
 * Created by deng on 2017/4/28.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void create(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public void delete(Integer id) {
        this.userRepository.delete(id);
    }

    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findOne(Integer id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findByUserName(name);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        return this.userRepository.findByPhone(phone);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Long count() {
        return this.userRepository.count();
    }

    @Override
    public User verify(String userNorEorP, String password) {
        User user = verifyNameAndPass(userNorEorP, password);

        if (user != null) { return user; }

        user = verifyEmailAndPass(userNorEorP, password);
        if (user != null) { return user; }

        user = verifyPhoneAndPass(userNorEorP, password);
        if (user != null) { return  user; }

        return null;
    }

    public User verifyNameAndPass(String username, String password) {
        User user = findByName(username);
        if (user == null) return null;

        if (user.getPasswordMD5().equals(Util.MD5(password))) {
            return user;
        }
        return null;
    }


    public User verifyEmailAndPass(String email, String password) {
        User user = findByEmail(email);
        if (user == null) return null;

        if (user.getPasswordMD5().equals(Util.MD5(password))) {
            return user;
        }
        return null;
    }

    public User verifyPhoneAndPass(String phone, String password) {
        User user = findByPhone(phone);
        if (user == null) return null;

        if (user.getPasswordMD5().equals(Util.MD5(password))) {
            return user;
        }
        return null;
    }
}
