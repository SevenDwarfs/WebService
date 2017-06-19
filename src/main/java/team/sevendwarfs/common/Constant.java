package team.sevendwarfs.common;

import static strman.Strman.repeat;

/**
 * Created by deng on 2017/6/3.
 */
public class Constant {
    /**
     * 在 service.MovieServiceImpl 中使用
     */
    static public Integer searchMovieTypeNumber = 20;

    static public Integer seatsNumber = 11 * 8;
    /**
     * 座位的最大行列数
     * 在SeatUtil中用到
     */
    static public Integer seatRow = 8;
    static public Integer seatCol = 11;

    static public char vacancy = '0';
    static public char lock = '1';
    static public char sold = '2';

    public static Integer FREE = 0;
    public static Integer LOCK = 1;
    public static Integer SOLD = 2;

    static  public String vacancySeat = repeat("0", seatsNumber);
}
