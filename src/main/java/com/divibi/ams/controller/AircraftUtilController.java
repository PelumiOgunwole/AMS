package com.divibi.ams.controller;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.AircraftUtilization;
import com.divibi.ams.model.Component;
import com.divibi.ams.model.Worker;
import com.divibi.ams.repository.AirCraftUtilizationRepository;
import com.divibi.ams.repository.ComponentRepository;
import com.divibi.ams.repository.WorkerRepository;
import com.divibi.ams.service.AircraftService;
import com.divibi.ams.service.AircraftUtilizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
@Controller
@RequestMapping("/au")
public class AircraftUtilController {

        private final AircraftUtilizationService aircraftService;


    public AircraftUtilController(AircraftUtilizationService aircraftService) {
        this.aircraftService = aircraftService;
    }

        @GetMapping( "/all-aircrafts-util"  )
        public String viewAircraftUtilizationHomePage (Model model, HttpSession session) {
            session.setAttribute("freshLoad", true);
            return getPaginated(1,model,null,session);
        }

        @GetMapping("/show-new-aircraft-util")
        public String showNewAircraftUtilization(Model model) {

            AircraftUtilization aircraft = new AircraftUtilization();
            model.addAttribute("aircraft",aircraft);

            return "aircraft_utilization/new_aircraft_util";
        }
        @GetMapping("/show-aircraft-util-details/{id}")
        public String showAircraftUtilizationDetails(@PathVariable(value = "id") Long id, Model model) {
            AircraftUtilization aircraft = aircraftService.getAircraftUtilizationById(id);
            model.addAttribute("details",aircraft);
            return "aircraft_utilization/aircraft_util_details";
        }

        @PostMapping("/save-aircraft-util")
        public String saveAircraftUtilization(@ModelAttribute("aircraftUtil") AircraftUtilization aircraftUtilization, BindingResult bindingResult) throws ParseException {
            if(bindingResult.hasErrors()){
                return "aircraft_utilization/update_aircraft_util";
            }

            aircraftService.saveAircraftUtilization(aircraftUtilization);
            return "redirect:/au/all-aircrafts-util";
        }
        @GetMapping("/search-aircraft-util")
        public String searchAircraftUtilization(@RequestParam("keyWord") String keyWord,
                                     @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                     Model model) {
            List<AircraftUtilization> filteredAircraft = aircraftService.findAircraftUtilizationByKeyWord(keyWord);
            Page<AircraftUtilization> page = new PageImpl<>(filteredAircraft); // Create a custom Page for the filtered data
            List<AircraftUtilization> listOfAirCraftUtil = page.getContent();

            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("totalContent", page.getTotalElements());
            model.addAttribute("listOfAirCrafts", listOfAirCraftUtil);
            model.addAttribute("keyWord", keyWord); // Retain the search keyword

            return "aircraft_utilization/aircraft_util";
        }

        @GetMapping("/show-update-aircraft-util-form/{id}")
        public String showUpdateAircraftUtilizationForm(@PathVariable (value = "id") Long id, Model model) {
            AircraftUtilization aircraft = aircraftService.getAircraftUtilizationById(id);
            model.addAttribute("aircraft",aircraft);
            return "aircraft_utilization/update_aircraft_util";
        }

        @GetMapping("/delete-aircraft/{id}")
        public String deleteAircraftUtilizationById(@PathVariable (value = "id") Long id) {
            aircraftService.getAircraftUtilizationById(id);
            return "redirect:/au/all-aircrafts-util";
        }

        @GetMapping("/all-aircrafts-util/page/{pageNo}")
        public String getPaginated(@PathVariable(value = "pageNo") Integer pageNo, Model model,
                                   @RequestParam(value = "keyWord", required = false) String keyWord,HttpSession session) {
            int pageSize = 5;
            Page<AircraftUtilization> page = aircraftService.findPaginated(pageNo, pageSize);

            List<AircraftUtilization> listOfAirCraftUtil = page.getContent();
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("totalContent", page.getTotalElements());
            model.addAttribute("listOfAirCrafts", listOfAirCraftUtil);
            model.addAttribute("keyWord", keyWord); // Retain the search keyword
            session.setAttribute("freshLoad", false); // Set to false after initial load

            return "aircraft_utilization/aircraft_util";
        }

    }

