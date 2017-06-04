package team.sevendwarfs.web.controller.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.ResponseState;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.service.MovieService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by deng on 2017/6/3.
 */

@Controller
@RequestMapping("/api/admin")
public class manageMovieController {
    @Autowired
    MovieService movieService;

    /**
     * 管理员添加新的电影
     * @param chineseName
     * @param englishName
     * @param url
     * @param type
     * @param length
     * @param releaseDate 格式要求 yyyymmdd 20170603
     * @param introduction
     * @return
     */
    @PostMapping("/newMovie")
    @ResponseBody
    public ResponseState setNewMovie(@RequestParam(value = "chineseName",
            required = true) String chineseName,
                                     @RequestParam(value = "englishName",
                                             required = true) String englishName,
                                     @RequestParam(value = "pictureUrl",
                                             required = true) String url,
                                     @RequestParam(value = "type",
                                             required = true) String type,
                                     @RequestParam(value = "length",
                                             required = true) String length,
                                     @RequestParam(value = "releaseDate",
                                             required = true) String
                                                 releaseDate,
                                     @RequestParam(value = "introduction",
                                             required = true) String introduction) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = sdf.parse(releaseDate);
        } catch (ParseException e) {
            date = new Date();
        }
        Movie movie = new Movie(chineseName, englishName, type, length, url,
                date, introduction);

        movieService.create(movie);

        return new ResponseState(ResponseState.SUCCESS);
    }


    /**
     * 根据id删除电影
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseState deleteMovieById(@PathVariable("id") Integer id) {
        movieService.delete(id);

        return  new ResponseState(ResponseState.SUCCESS);
    }

    /**
     * 根据id查询电影并修改电影数据
     * @param id
     * @param chineseName
     * @param englishName
     * @param url
     * @param type
     * @param length
     * @param releaseDate
     * @param introduction
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseState updateMovieById(@PathVariable("id") Integer id,
                                         @RequestParam(value = "chineseName",
                                                 required = false, defaultValue="") String chineseName,
                                         @RequestParam(value = "englishName",
                                                 required = false, defaultValue="") String englishName,
                                         @RequestParam(value = "pictureUrl",
                                                 required = false, defaultValue="") String url,
                                         @RequestParam(value = "type",
                                                 required = false, defaultValue="") String type,
                                         @RequestParam(value = "length",
                                                 required = false, defaultValue="") String length,
                                         @RequestParam(value = "releaseDate",
                                                 required = false, defaultValue="") String releaseDate,
                                         @RequestParam(value = "introduction",
                                                 required = false, defaultValue="") String introduction) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return new ResponseState(ResponseState.ERROR, "该电影不存在");
        }

        if (!"".equals(chineseName)) {
            movie.setChineseName(chineseName);
        }
        if (!"".equals(englishName)) {
            movie.setEnglishName(englishName);
        }
        if (!"".equals(url)) {
            movie.setUrl(url);
        }
        if (!"".equals(type)) {
            movie.setType(type);
        }
        if (!"".equals(length)) {
            movie.setLength(length);
        }
        if (!"".equals(introduction)) {
            movie.setIntroduction(introduction);
        }
        if (!"".equals(releaseDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = null;
            try {
                date = sdf.parse(releaseDate);
            } catch (ParseException e) {
                date = new Date();
            }
            movie.setReleaseDate(date);
        }

        movieService.update(movie);

        return new ResponseState(ResponseState.SUCCESS);
    }

}
