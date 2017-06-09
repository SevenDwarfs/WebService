package team.sevendwarfs.web.model;

import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.MoviePicture;
import team.sevendwarfs.persistence.entities.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by deng on 2017/6/9.
 */
public class MovieModel {
    private Integer id;
    private String chineseName;
    private String englishName;
    private String type;
    private String length;
    private String url;
    private Date releaseDate;
    private String rating;
    private String country;
    private String showPlace;
    private String introduction;
    private List<MoviePicture> pictures;
    private List<Person> actors;
    private List<Person> directors;

    private List<Person> moviersToActorDirect(List<Person> moviers, String
            type) {
        List<Person> temp = new ArrayList<>();
        for (Person person : moviers) {
            if (person.getType().equals(type)) {
                temp.add(person);
            }
        }
        return temp;
    }

    public MovieModel() {
    }

    public MovieModel(Movie movie) {
        this.id = movie.getId();
        this.chineseName = movie.getChineseName();
        this.englishName = movie.getEnglishName();
        this.type = movie.getType();
        this.length = movie.getLength();
        this.url = movie.getUrl();
        this.releaseDate = movie.getReleaseDate();
        this.rating = movie.getRating();
        this.country = movie.getCountry();
        this.showPlace = movie.getShowPlace();
        this.introduction = movie.getIntroduction();
        this.pictures = movie.getPictures();
        this.actors = moviersToActorDirect(movie.getMoviers(), Person.ACTOR);
        this.directors = moviersToActorDirect(movie.getMoviers(), Person.DIRECTOR);
    }

    public MovieModel(Integer id, String chineseName, String englishName, String type, String length, String url, Date releaseDate, String rating, String country, String showPlace, String introduction, List<MoviePicture> pictures, List<Person> actors, List<Person> directors) {
        this.id = id;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.type = type;
        this.length = length;
        this.url = url;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.country = country;
        this.showPlace = showPlace;
        this.introduction = introduction;
        this.pictures = pictures;
        this.actors = actors;
        this.directors = directors;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShowPlace() {
        return showPlace;
    }

    public void setShowPlace(String showPlace) {
        this.showPlace = showPlace;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }

    public List<MoviePicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<MoviePicture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", type='" + type + '\'' +
                ", length='" + length + '\'' +
                ", url='" + url + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating='" + rating + '\'' +
                ", country='" + country + '\'' +
                ", showPlace='" + showPlace + '\'' +
                ", introduction='" + introduction + '\'' +
                ", pictures=" + pictures +
                ", actors=" + actors +
                ", directors=" + directors +
                '}';
    }
}
