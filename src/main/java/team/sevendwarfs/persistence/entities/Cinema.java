package team.sevendwarfs.persistence.entities;

import team.sevendwarfs.web.model.CinemaModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */

@Entity
@Table(name = "cinema")
public class Cinema {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "cinema")
    private List<Screen> screens;

    public Cinema() {
    }

    public Cinema(String name, String address, String phone, List<Screen> screens) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.screens = screens;
    }

    public Cinema(CinemaModel cinemaModel) {
        this.name = cinemaModel.getName();
        this.address = cinemaModel.getAddress();
        this.phone = cinemaModel.getPhone();
        this.screens = cinemaModel.getScreens();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", screens=" + screens +
                '}';
    }
}
