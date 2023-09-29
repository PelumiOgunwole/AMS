package com.divibi.ams.controller;

import com.divibi.ams.model.AirCraftMaintenances;

import com.divibi.ams.service.AircraftMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/m")
public class AircraftMaintenanceController {
    @Autowired
    private final AircraftMaintenanceService aircraftService;


    public AircraftMaintenanceController(AircraftMaintenanceService aircraftService) {
        this.aircraftService = aircraftService;

    }

    @RequestMapping(value = "/aircraft-maintenance",method = RequestMethod.GET  )
    public String viewAircraftMaintenanceHomePage (Model model, HttpSession session) {
        session.setAttribute("freshLoad", true);
        return getPaginated(1,model,null,session);
    }

    @GetMapping("/show-new-aircraft-maintenance")
    public String showNewAircraftMaintenance(Model model) {

        AirCraftMaintenances aircraft = new AirCraftMaintenances();
        model.addAttribute("aircraft",aircraft);

        return "maintenance/new_maintenance";
    }
    @GetMapping("/show-aircraft-maintenance-details/{id}")
    public String showAircraftMaintenanceDetails(@PathVariable (value = "id") Long id, Model model) {
        AirCraftMaintenances aircraft = aircraftService.getAircraftMaintenanceById(id);
        model.addAttribute("details",aircraft);
        return "maintenance/maintenance_details";
    }

    @PostMapping("/save-aircraft-maintenance")
    public String saveAircraftMaintenance(@ModelAttribute("aircraft") AirCraftMaintenances aircraft)  {
//        if(bindingResult.hasErrors()){
//            return "maintenance/update_maintenance";
//        }

        aircraftService.saveAircraftMaintenance(aircraft);
        return "redirect:/m/aircraft-maintenance";
    }
    @GetMapping("/search-aircraft-maintenance")
    public String searchAircraftMaintenance(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                 Model model) {
        List<AirCraftMaintenances> filteredAircraftMaintenance = aircraftService.findAirCraftMaintenanceByKeyWord(keyWord);
        Page<AirCraftMaintenances> page = new PageImpl<>(filteredAircraftMaintenance); // Create a custom Page for the filtered data
        List<AirCraftMaintenances> listOfAirCraft = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfAirCrafts", listOfAirCraft);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword

        return "maintenance/maintenance";
    }

    @GetMapping("/show-update-aircraft-maintenance-form/{id}")
    public String showUpdateAircraftMaintenanceForm(@PathVariable (value = "id") Long id, Model model) {
        AirCraftMaintenances aircraft = aircraftService.getAircraftMaintenanceById(id);
        model.addAttribute("air",aircraft);
        return "maintenance/update_maintenance";
    }

    @GetMapping("/delete-aircraft-maintenance/{id}")
    public String deleteAircraftMaintenanceById(@PathVariable (value = "id") Long id) {
        aircraftService.deleteAircraftMaintenance(id);
        return "redirect:/aircraft-maintenance";
    }

    @GetMapping("/all-aircrafts/page/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") Integer pageNo, Model model,
                               @RequestParam(value = "keyWord", required = false) String keyWord,HttpSession session) {
        int pageSize = 2;
        Page<AirCraftMaintenances> page = aircraftService.findPaginated(pageNo, pageSize);

        List<AirCraftMaintenances> listOfAirCraftMaintenance = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfAirCraftMaintenance", listOfAirCraftMaintenance);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword
        session.setAttribute("freshLoad", false); // Set to false after initial load

        return "maintenance/maintenance";
    }

}
