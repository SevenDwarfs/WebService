package team.sevendwarfs.business.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by deng on 2017/4/23.
 */

@Entity
@Table(name = "movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    // 中文名字
    @Column(name = "chinese_name")
    private  String chineseName;

    // 英文名字
    @Column(name = "english_name")
    private String englishName;

    // 电影类型
    @Column(name = "type")
    private String type;

    // 电影长度
    @Column(name = "length")
    private String length;

    // 电影海报URL
    @Column(name = "url")
    private String url;

    // 上映时间
    @Column(name = "release_date")
    private Date releaseDate;

    // 电影简介
    @Column(name = "introduction")
    private String introduction;

    // 电影人
    @Column(name = "moiver")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_person",
            joinColumns = @JoinColumn(name = "mid"),
            inverseJoinColumns = @JoinColumn(name = "pid")
    )
    private  Set<Person> moivers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Set<Person> getMoivers() {
        return moivers;
    }

    public void setMoivers(Set<Person> moivers) {
        this.moivers = moivers;
    }
}
