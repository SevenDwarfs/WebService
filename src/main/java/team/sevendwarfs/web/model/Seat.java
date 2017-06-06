package team.sevendwarfs.web.model;

import java.util.List;

/**
 * Created by deng on 2017/6/6.
 */
public class Seat {
    private List<Integer> vacancy;

    private List<Integer> soldOut;

    private List<Integer> locking;

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

    @Override
    public String toString() {
        return "Seat{" +
                "vacancy=" + vacancy +
                ", soldOut=" + soldOut +
                ", locking=" + locking +
                '}';
    }
}

