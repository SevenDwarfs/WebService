package team.sevendwarfs.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sevendwarfs.common.Util;
import team.sevendwarfs.persistence.entities.Admin;
import team.sevendwarfs.persistence.repository.AdminRepository;

import java.util.List;

/**
 * Created by deng on 2017/4/28.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public void create(Admin admin) {
        this.adminRepository.save(admin);
    }

    @Override
    public void delete(Admin admin) {
        this.adminRepository.delete(admin);
    }

    @Override
    public void delete(Integer id) {
        this.adminRepository.delete(id);
    }

    @Override
    public Admin update(Admin admin) {
        return this.adminRepository.save(admin);
    }

    @Override
    public Admin findOne(Integer id) {
        return this.adminRepository.findOne(id);
    }

    @Override
    public Admin findByName(String name) {
        return this.adminRepository.findByAdminName(name);
    }

    @Override
    public Admin findByEmail(String email) {
        return this.adminRepository.findByEmail(email);
    }

    @Override
    public Admin findByPhone(String phone) {
        return this.adminRepository.findByPhone(phone);
    }

    @Override
    public List<Admin> findAll() {
        return this.adminRepository.findAll();
    }

    @Override
    public Long count() {
        return this.adminRepository.count();
    }

    @Override
    public Admin verify(String adminNorEorP, String password) {
        Admin admin = verifyNameAndPass(adminNorEorP, password);

        if (admin != null) { return admin; }

        admin = verifyEmailAndPass(adminNorEorP, password);
        if (admin != null) { return admin; }

        admin = verifyPhoneAndPass(adminNorEorP, password);
        if (admin != null) { return  admin; }

        return null;
    }

    public Admin verifyNameAndPass(String adminname, String password) {
        Admin admin = findByName(adminname);
        if (admin == null) return null;

        if (admin.getPasswordMD5().equals(Util.MD5(password))) {
            return admin;
        }
        return null;
    }


    public Admin verifyEmailAndPass(String email, String password) {
        Admin admin = findByEmail(email);
        if (admin == null) return null;

        if (admin.getPasswordMD5().equals(Util.MD5(password))) {
            return admin;
        }
        return null;
    }

    public Admin verifyPhoneAndPass(String phone, String password) {
        Admin admin = findByPhone(phone);
        if (admin == null) return null;

        if (admin.getPasswordMD5().equals(Util.MD5(password))) {
            return admin;
        }
        return null;
    }
}
