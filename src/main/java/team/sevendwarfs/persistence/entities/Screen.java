package team.sevendwarfs.persistence.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 场次
 * Created by deng on 2017/6/5.
 */

@Entity
@Table(name = "screen")
public class Screen {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time")
    private Date time;

    @Column(name = "language")
    private String language;

    @Column(name = "room")
    private String room;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cinema_id")
    private Cinema cinema;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "seats")
    private String seats;

    public Screen() {
    }

    public Screen(Date time, String language, String room, Double price, Cinema cinema, String movieName, String seats) {
        this.time = time;
        this.language = language;
        this.room = room;
        this.price = price;
        this.cinema = cinema;
        this.movieName = movieName;
        this.seats = seats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", time=" + time +
                ", language='" + language + '\'' +
                ", room='" + room + '\'' +
                ", price=" + price +
                ", cinema=" + cinema +
                ", movieName='" + movieName + '\'' +
                ", seats=" + seats +
                '}';
    }
}
