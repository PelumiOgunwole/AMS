package com.divibi.ams.controller;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AircraftController {
    @Autowired
    private  AircraftService aircraftService;

    @RequestMapping(value = "/all-aircrafts",method = RequestMethod.GET  )
    public String viewAircraftHomePage (Model model) {
        return getPaginated(1,model);
    }

    @GetMapping("/show-new-aircraft")
    public String showNewAircraft(Model model) {
        Aircraft aircraft = new Aircraft();
        model.addAttribute("aircraft",aircraft);
        return "new_aircraft";
    }
    @PostMapping("/save-aircraft")
    public String saveAircraft(@ModelAttribute("aircraft") Aircraft aircraft) {
        aircraftService.saveAircraft(aircraft);
        return "redirect:/all-aircrafts";
    }
    @GetMapping("/show-update-aircraft-form/{id}")
    public String showUpdateAircraftForm(@PathVariable (value = "id") Integer id, Model model) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        model.addAttribute("aircraft",aircraft);
        return "update_aircraft";
    }
    @GetMapping("/delete-aircraft/{id}")
    public String deleteAircraftById(@PathVariable (value = "id") Integer id) {

        aircraftService.deleteAircraft(id);
        return "redirect:/all-aircrafts";
    }

    @GetMapping("/page/{pageNo}")
    public String getPaginated(@PathVariable (value = "pageNo") Integer pageNo,Model model) {
        int pageSize =10;
        Page<Aircraft> page =aircraftService.findPaginated(pageNo,pageSize);
        List<Aircraft> listOfAirCraft = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("totalContent",page.getTotalElements());
        model.addAttribute("listOfAirCrafts",listOfAirCraft);

        return "index";
    }

}
