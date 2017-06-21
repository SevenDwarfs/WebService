package team.sevendwarfs.common;

import team.sevendwarfs.web.model.Seat;

import java.util.List;

/**
 * Created by deng on 2017/6/6.
 */
public class SeatUtil {
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

    static public void changeSeatState(String seat, StringBuffer seatBuffer) {
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
