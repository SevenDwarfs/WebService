package team.sevendwarfs.common;

import org.apache.tomcat.util.bcel.Const;
import team.sevendwarfs.web.model.Seat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by deng on 2017/6/6.
 */
public class SeatUtil {
    static public char judgeType(String seat) {
        char type = '#';
        Set<Character> s = new HashSet<>();
        int num = 0;
        for (int i = 0; i < seat.length(); i++) {
            if (!s.contains(seat.charAt(i))) { num++; s.add(seat.charAt(i)); }
            if (seat.charAt(i) == Constant.lock) {
                type = Constant.lock;
            } else if (seat.charAt(i) == Constant.sold) {
                type = Constant.sold;
            }
        }
        if (num == 1) { type = Constant.vacancy; }

        if (num > 2) { return '#'; }

        return type;
    }

    static public boolean validSeatVacancy(String seat, StringBuffer
            seatBuffer) {
        for (int i = 0; i < seat.length(); i++) {
            if (seat.charAt(i) == Constant.vacancy) {
                if (seatBuffer.charAt(i) != Constant.vacancy &&
                        seatBuffer.charAt(i) != Constant.lock) return false;
            }
        }
        return true;
    }

    static public boolean validSeatLock(String seat, StringBuffer seatBuffer) {
        for (int i = 0; i < seat.length(); i++) {
            if (seat.charAt(i) == Constant.lock) {
                if (seatBuffer.charAt(i) != Constant.vacancy) { return  false; }
            }
        }

        return true;
    }

    static public boolean validSeatSold(String seat, StringBuffer seatBuffer) {
        for (int i = 0; i < seat.length(); i++) {
            if (seat.charAt(i) == Constant.sold) {
                if (seatBuffer.charAt(i) != Constant.lock) { return  false; }
            }
        }

        return true;

    }

    static public void changeSeatState(String seat, StringBuffer seatBuffer,
                                       char type) {
        if (type == Constant.vacancy) {
            for (int i = 0; i < seat.length(); i++) {
                if (seat.charAt(i) == Constant.vacancy) {
                    seatBuffer.setCharAt(i, Constant.vacancy);
                }
            }
        }

        for (int i = 0; i < seat.length(); i++) {
            if (seat.charAt(i) == Constant.lock) {
                seatBuffer.setCharAt(i, Constant.lock);
            }
        }

        for (int i = 0; i < seat.length(); i++) {
            if (seat.charAt(i) == Constant.sold) {
                seatBuffer.setCharAt(i, Constant.sold);
            }
        }
    }
}
