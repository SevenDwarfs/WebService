package team.sevendwarfs.common;

import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.web.model.SimpCinema;
import team.sevendwarfs.web.model.SimpMovie;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系统工具类
 * Created by deng on 2017/4/26.
 */
public class Util {
    static private String phoneRegex = "^[1][3,4,5,7,8][0-9]{9}$";
    static private String emailRegex = "^[A-Za-zd]+([-_.][A-Za-zd]+)*@" +
            "([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$";
    /**
     * @Description 得到 MD5 摘要值
     * @param input 输入字符串
     * @return input 的 MD5
     */
    static public String MD5(String input) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());

            //BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 密码要求 6-100 位
     * @param password
     * @return
     */
    static public boolean validPassword(String password) {
        boolean flag = true;
        int len = password.length();
        if (len < 6 || len > 100) { flag = false; }

        return flag;
    }

    /**
     * 手机号合法性验证
     * @param phone
     * @return
     */
    static public boolean validPhone(String phone) {
        Pattern pattern = Pattern.compile(phoneRegex); // 验证手机号
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * 邮箱合法性验证
     * @param email
     * @return
     */
    static public boolean validEmail(String email) {
        Pattern regex = Pattern.compile(emailRegex);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    /**
     * 将movies列表封装成SimpMovie列表返回
     * @param movies
     * @return
     */
    static public List<SimpMovie> MoviesToSimpMovies(List<Movie> movies) {
        List<SimpMovie> simpMovies = new ArrayList<>();

        for (Movie movie : movies) {
            simpMovies.add(new SimpMovie(movie));
        }
        return simpMovies;
    }

    /**
     * 将cinema列表封装成SinmCinema列表返回
     * @param cinemas
     * @return
     */
    static public List<SimpCinema> CinemaToSimpCinema(List<Cinema> cinemas) {
        List<SimpCinema> simpCinemas = new ArrayList<>();

        for (Cinema cinema : cinemas) {
            simpCinemas.add(new SimpCinema(cinema));
        }
        return simpCinemas;
    }
}