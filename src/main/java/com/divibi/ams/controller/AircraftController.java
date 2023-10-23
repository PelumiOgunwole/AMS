package com.divibi.ams.controller;

import com.divibi.ams.model.AirCraftMaintenances;
import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import com.divibi.ams.model.Worker;
import com.divibi.ams.repository.AircraftMaintenanceRepository;
import com.divibi.ams.repository.ComponentRepository;
import com.divibi.ams.repository.WorkerRepository;
import com.divibi.ams.service.AircraftService;
import com.divibi.ams.service.CalendarService;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/")
public class AircraftController {
    @Autowired
    private final AircraftService aircraftService;
    private  final WorkerRepository workerRepository;
    private  final ComponentRepository componentRepository;
    private  final AircraftMaintenanceRepository maintenanceRepository;

    @Autowired
    CalendarService calendarService;

    public AircraftController(AircraftService aircraftService, WorkerRepository workerRepository, ComponentRepository componentRepository, AircraftMaintenanceRepository maintenanceRepository) {
        this.aircraftService = aircraftService;
        this.workerRepository = workerRepository;
        this.componentRepository = componentRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    @RequestMapping(value = "/all-aircrafts",method = RequestMethod.GET  )
    public String viewAircraftHomePage (Model model, HttpSession session) {
        session.setAttribute("freshLoad", true);
        return getPaginated(1,model,null,session);
    }

    @GetMapping("/show-new-aircraft")
    public String showNewAircraft(Model model) {
        List<Worker> workers = workerRepository.findAll();
        List<Component> components = componentRepository.findAll();
        List<AirCraftMaintenances> maintenances = maintenanceRepository.findAll();
        Aircraft aircraft = new Aircraft();
        model.addAttribute("aircraft",aircraft);
        model.addAttribute("listOfWorkers",workers);
        model.addAttribute("listOfComponents",components);
        model.addAttribute("listOfMaintenances",maintenances);
        return "aircraft/new_aircraft";
    }

    @GetMapping("/show-update-aircraft-form/{id}")
    public String showUpdateAircraftForm(@PathVariable (value = "id") Long id, Model model) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        List<Worker> workers = workerRepository.findAll();
        List<Component> components = componentRepository.findAll();
        List<AirCraftMaintenances> maintenances = maintenanceRepository.findAll();
        model.addAttribute("aircraft",aircraft);
        model.addAttribute("listOfWorkers",workers);
        model.addAttribute("listOfComponents",components);
        model.addAttribute("listOfMaintenances",maintenances);
        return "aircraft/update_aircraft";
    }
    @GetMapping("/show-aircraft-details/{id}")
    public String showAircraftDetails(@PathVariable (value = "id") Long id, Model model) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        List<Worker> workers = workerRepository.findAll();
        List<AirCraftMaintenances> maintenances = maintenanceRepository.findAll();
        List<Component> components = componentRepository.findAll();
        model.addAttribute("listOfWorkers",workers);
        model.addAttribute("listOfComponents",components);
        model.addAttribute("maintenances",maintenances);
        model.addAttribute("details",aircraft);
        return "aircraft/aircraft_details";
    }

    @PostMapping("/save-aircraft")
    public String saveAircraft(@ModelAttribute("aircraft") Aircraft aircraft,BindingResult bindingResult) throws ParseException {
        if(bindingResult.hasErrors()){
            return "aircraft/update_aircraft";
        }

        aircraftService.saveAircraft(aircraft);
        return "redirect:/all-aircrafts";
    }
    @GetMapping("/search-aircraft")
    public String searchAircraft(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                 Model model) {
        List<Aircraft> filteredAircraft = aircraftService.findAirCraftByKeyWord(keyWord);
        Page<Aircraft> page = new PageImpl<>(filteredAircraft); // Create a custom Page for the filtered data
        List<Aircraft> listOfAirCraft = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfAirCrafts", listOfAirCraft);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword

        return "index";
    }



    @GetMapping("/delete-aircraft/{id}")
    public String deleteAircraftById(@PathVariable (value = "id") Long id) {
        aircraftService.deleteAircraft(id);
        return "redirect:/all-aircrafts";
    }

    @GetMapping("/all-aircrafts/page/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") Integer pageNo, Model model,
                               @RequestParam(value = "keyWord", required = false) String keyWord,HttpSession session) {
        int pageSize = 5;
        Page<Aircraft> page = aircraftService.findPaginated(pageNo, pageSize);

        List<Aircraft> listOfAirCraft = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfAirCrafts", listOfAirCraft);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword
        session.setAttribute("freshLoad", false); // Set to false after initial load

        return "index";
    }

    @GetMapping("/syncAndView/{aircraftId}")
    public ResponseEntity<List<Event>> syncAndViewAircraftMaintenance(@PathVariable Long aircraftId, Model model) throws Exception {
        // Assuming you have a method to get Aircraft by Id
        Aircraft aircraft = aircraftService.getAircraftById (aircraftId);

        // Sync with Google Calendar
        aircraftService.syncAircraftMaintenanceToGoogleCalendar(aircraft);

        // Fetch Events from Google Calendar
        List<Event> events = calendarService.getEvents();
        model.addAttribute("aircraftEvent", aircraft);
        // Add events to model
        model.addAttribute("calendarEvents", events);
        return  ResponseEntity.ok(events);

//        return "aircraft/calendar";
    }

}
