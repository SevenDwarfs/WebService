package team.sevendwarfs.persistence.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by deng on 2017/4/26.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "password_md5")
    private String passwordMD5;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<FilmOrder> filmOrderList;

    public User() {}

    public User(String name, String email, String phone, String
            passwordMD5) {
        this.userName = name;
        this.email = email;
        this.phone = phone;
        this.passwordMD5 = passwordMD5;
    }

    public User(String userName, String passwordMD5, String email, String phone, List<FilmOrder> filmOrderList) {
        this.userName = userName;
        this.passwordMD5 = passwordMD5;
        this.email = email;
        this.phone = phone;
        this.filmOrderList = filmOrderList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<FilmOrder> getFilmOrderList() {
        return filmOrderList;
    }

    public void setFilmOrderList(List<FilmOrder> filmOrderList) {
        this.filmOrderList = filmOrderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passwordMD5='" + passwordMD5 + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", filmOrderList=" + filmOrderList +
                '}';
    }
}
