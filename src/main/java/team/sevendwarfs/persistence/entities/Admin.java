package team.sevendwarfs.persistence.entities;

import javax.persistence.*;

/**
 * Created by deng on 2017/6/3.
 */
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "admin_name", unique = true, nullable = false)
    private String adminName;

    @Column(name = "password_md5", nullable = false)
    private String passwordMD5;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    public Admin() {}

    public Admin(String adminName, String passwordMD5, String email, String phone) {
        this.adminName = adminName;
        this.passwordMD5 = passwordMD5;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPasswordMD5() {
        return passwordMD5;
    }

    public void setPasswordMD5(String passwordMD5) {
        this.passwordMD5 = passwordMD5;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", passwordMD5='" + passwordMD5 + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
