package team.sevendwarfs.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by deng on 2017/6/8.
 */

@Entity
@Table(name = "film_order")
public class FilmOrder {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @Column(name = "screen_id")
    private Integer screenId;

    @Column(name = "seat")
    private String seat;

    public FilmOrder() {
    }

    public FilmOrder(User user, Integer screenId, String seat) {
        this.user = user;
        this.screenId = screenId;
        this.seat = seat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "FilmOrder{" +
                "id=" + id +
                ", user=" + user +
                ", screenId=" + screenId +
                ", seat='" + seat + '\'' +
                '}';
    }
}
