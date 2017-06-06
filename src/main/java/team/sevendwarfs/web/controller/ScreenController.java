package team.sevendwarfs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.ResponseState;
import team.sevendwarfs.common.SeatUtil;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.entities.Movie;
import team.sevendwarfs.persistence.entities.Screen;
import team.sevendwarfs.persistence.service.CinemaService;
import team.sevendwarfs.persistence.service.MovieService;
import team.sevendwarfs.persistence.service.ScreenService;
import team.sevendwarfs.web.model.Seat;

import java.util.List;

/**
 * Created by deng on 2017/6/5.
 */

@Controller
@RequestMapping("/api/screen")
public class ScreenController {
    @Autowired
    ScreenService screenService;

    @Autowired
    MovieService movieService;

    @Autowired
    CinemaService cinemaService;

    /**
     * 通过影院和电影啦查询场次信息
     * @param cinemaId
     * @param movieId
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<Screen> getScreenByCinemaAndMovie(@RequestParam(value="cinemaid") Integer cinemaId,
                                                  @RequestParam(value="movieid") Integer movieId) {
        Cinema cinema = cinemaService.findById(cinemaId);
        Movie movie = movieService.findById(movieId);

        if (cinema == null || movie == null) { return null; }

        return screenService.findByCinemaAndMovie(cinema, movie);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Screen getScreenById(@PathVariable("id") Integer id) {
        return screenService.findById(id);
    }

    /**
     * 锁定/出售座位
     * @param id
     * @param seat
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseState putSeat(@PathVariable("id") Integer id,
                                 @RequestBody Seat seat) {
        Screen screen = screenService.findById(id);
        StringBuffer seatBuffer = new StringBuffer(screen.getSeats());

        if (SeatUtil.validSeatLock(seat, seatBuffer)) {
            return new ResponseState(ResponseState.ERROR, "锁定座位失败,座位已经被锁定或售出");
        };

        if (SeatUtil.validSeatSold(seat, seatBuffer)) {
            return new ResponseState(ResponseState.ERROR, "购买座位失败,座位未被锁定");
        };

        SeatUtil.changeSeatState(seat, seatBuffer);
        screen.setSeats(new String(seatBuffer));
        screenService.update(screen);

        return new ResponseState(ResponseState.SUCCESS);
    }
}
