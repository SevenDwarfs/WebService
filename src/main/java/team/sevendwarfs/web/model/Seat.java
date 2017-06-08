package team.sevendwarfs.web.model;

import team.sevendwarfs.common.Constant;

import java.util.List;

/**
 * Created by deng on 2017/6/6.
 */
public class Seat {
    private List<Integer> vacancy;

    private List<Integer> locking;

    private List<Integer> soldOut;

    public Seat() {
    }

    public Seat(List<Integer> vacancy, List<Integer> soldOut, List<Integer> locking) {
        this.vacancy = vacancy;
        this.soldOut = soldOut;
        this.locking = locking;
    }

    public List<Integer> getVacancy() {
        return vacancy;
    }

    public void setVacancy(List<Integer> vacancy) {
        this.vacancy = vacancy;
    }

    public List<Integer> getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(List<Integer> soldOut) {
        this.soldOut = soldOut;
    }

    public List<Integer> getLocking() {
        return locking;
    }

    public void setLocking(List<Integer> locking) {
        this.locking = locking;
    }

    public String toSeatForm() {
        int len = Constant.searchMovieTypeNumber;
        StringBuffer seatBuffer = new StringBuffer(len);

        for (int i = 0; i < len; i++) {
            seatBuffer.setCharAt(i, Constant.vacancy);
        }

        for (Integer it : locking) {
            seatBuffer.setCharAt(it, Constant.lock);
        }

        for (Integer it : soldOut) {
            seatBuffer.setCharAt(it, Constant.sold);
        }
        return new String(seatBuffer);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "vacancy=" + vacancy +
                ", soldOut=" + soldOut +
                ", locking=" + locking +
                '}';
    }
}

