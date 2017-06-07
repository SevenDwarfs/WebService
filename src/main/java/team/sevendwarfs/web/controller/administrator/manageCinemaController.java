package team.sevendwarfs.web.controller.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.sevendwarfs.common.ResponseState;
import team.sevendwarfs.persistence.entities.Cinema;
import team.sevendwarfs.persistence.service.CinemaService;
import team.sevendwarfs.web.model.CinemaModel;

/**
 * Created by deng on 2017/6/7.
 */

@Controller
@RequestMapping("/api/admin/cinema")
public class manageCinemaController {
    @Autowired
    CinemaService cinemaService;

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseState deleteCinemaById(@PathVariable("id") Integer id) {
        cinemaService.delete(id);
        return new ResponseState(ResponseState.SUCCESS);
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseState createCinema(@RequestParam CinemaModel cinemaModel) {
        Cinema cinema = new Cinema(cinemaModel);
        if (cinemaService.findByName(cinemaModel.getName()) != null) {
            return new ResponseState(ResponseState.ERROR, "名字重复");
        }

        cinemaService.create(cinema);
        return new ResponseState(ResponseState.SUCCESS);
    }


    @PutMapping("/{id}")
    @ResponseBody
    public ResponseState updateCinema(@PathVariable("id") Integer id,
                                      @RequestParam CinemaModel cinemaModel) {
        Cinema cinema = cinemaService.findById(id);
        if (!cinemaModel.getName().equals("")) {
            cinema.setName(cinemaModel.getName());
        }

        if (!cinemaModel.getAddress().equals("")) {
            cinema.setAddress(cinemaModel.getAddress());
        }

        if (!cinemaModel.getPhone().equals("")) {
            cinema.setPhone(cinemaModel.getPhone());
        }

        if (cinemaModel.getScreens() != null) {
            cinema.setScreens(cinemaModel.getScreens());
        }

        return new ResponseState(ResponseState.SUCCESS);
    }
}
