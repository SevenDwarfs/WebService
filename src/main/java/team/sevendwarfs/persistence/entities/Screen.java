package team.sevendwarfs.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import team.sevendwarfs.common.Constant;

import javax.persistence.*;
import java.util.Date;

/**
 * 场次
 * Created by deng on 2017/6/5.
 */

@Entity
@Table(name = "screen")
public class Screen {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Column(name = "id")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "show_date")
    private Date showDate;

    @Column(name = "show_time")
    private String showTime;

    @Column(name = "language")
    private String language;

    @Column(name = "room")
    private String room;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cinema_id")
    @JsonIgnore
    private Cinema cinema;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "seats", length = 88)
    private String seats = Constant.vacancySeat;

    public Screen() {
    }

    public Screen(Date showDate, String showTime, String language, String room, Double price, Cinema cinema, String movieName, String seats) {
        this.showDate = showDate;
        this.showTime = showTime;
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

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
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

}
