package team.sevendwarfs.web.model;

import team.sevendwarfs.persistence.entities.User;


/**
 * Created by deng on 2017/6/3.
 */
public class UserModel {
    private String userName;

    private String email;

    private String phone;

    public UserModel() {}

    public UserModel(User user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public UserModel(String userName, String email, String phone) {

        this.userName = userName;
        this.email = email;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return "UserModel{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
