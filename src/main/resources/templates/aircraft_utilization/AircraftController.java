package com.divibi.ams.controller;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import com.divibi.ams.model.Worker;
import com.divibi.ams.repository.ComponentRepository;
import com.divibi.ams.repository.WorkerRepository;
import com.divibi.ams.service.AircraftService;
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
@RequestMapping("/")
public class AircraftController {
    @Autowired
    private final AircraftService aircraftService;
    private  final WorkerRepository workerRepository;
    private  final ComponentRepository componentRepository;

    public AircraftController(AircraftService aircraftService, WorkerRepository workerRepository,ComponentRepository componentRepository) {
        this.aircraftService = aircraftService;
        this.workerRepository = workerRepository;
        this.componentRepository =componentRepository;
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
        Aircraft aircraft = new Aircraft();
        model.addAttribute("aircraft",aircraft);
        model.addAttribute("listOfWorkers",workers);
        model.addAttribute("listOfComponents",components);
        return "aircraft/new_aircraft";
    }
    @GetMapping("/show-aircraft-details/{id}")
    public String showAircraftDetails(@PathVariable (value = "id") Long id, Model model) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        List<Worker> workers = workerRepository.findAll();
        List<Component> components = componentRepository.findAll();
        model.addAttribute("listOfWorkers",workers);
        model.addAttribute("listOfComponents",components);
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

    @GetMapping("/show-update-aircraft-form/{id}")
    public String showUpdateAircraftForm(@PathVariable (value = "id") Long id, Model model) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        model.addAttribute("aircraft",aircraft);
        return "aircraft/update_aircraft";
    }

    @GetMapping("/delete-aircraft/{id}")
    public String deleteAircraftById(@PathVariable (value = "id") Long id) {
        aircraftService.deleteAircraft(id);
        return "redirect:/all-aircrafts";
    }

    @GetMapping("/all-aircrafts/page/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") Integer pageNo, Model model,
                               @RequestParam(value = "keyWord", required = false) String keyWord,HttpSession session) {
        int pageSize = 2;
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

}
