package team.sevendwarfs.common;

import org.apache.tomcat.util.bcel.Const;
import team.sevendwarfs.web.model.Seat;

import java.util.List;

/**
 * Created by deng on 2017/6/6.
 */
public class SeatUtil {
    static public boolean validSeatLock(Seat seat, StringBuffer seatBuffer) {
        List<Integer> locks = seat.getLocking();
        for (Integer id : locks) {
            if (seatBuffer.charAt(id) != Constant.vacancy) { return false; }
        }

        return true;
    }

    static public boolean validSeatSold(Seat seat, StringBuffer seatBuffer) {
        List<Integer> solds = seat.getSoldOut();
        for (Integer id : solds) {
            if (seatBuffer.charAt(id) != Constant.lock) { return false; }
        }

        return true;

    }

    static public void changeSeatState(Seat seat, StringBuffer seatBuffer) {
        List<Integer> locks = seat.getLocking();
        for (Integer id : locks) {
            seatBuffer.setCharAt(id, Constant.lock);
        }

        List<Integer> sold = seat.getLocking();
        for (Integer id : sold) {
            seatBuffer.setCharAt(id, Constant.sold);
        }
    }
}
