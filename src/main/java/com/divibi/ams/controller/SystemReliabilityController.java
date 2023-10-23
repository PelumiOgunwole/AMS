package com.divibi.ams.controller;

import com.divibi.ams.model.PartsReliability;
import com.divibi.ams.model.SystemReliability;
import com.divibi.ams.repository.PartsReliabilityRepository;
import com.divibi.ams.repository.SystemReliabilityRepository;
import com.divibi.ams.service.PartsReliabilityService;
import com.divibi.ams.service.SystemReliabilityService;
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
public class SystemReliabilityController {

    @Autowired
    private SystemReliabilityService service;
    @Autowired
    private SystemReliabilityRepository repository;

    @RequestMapping(value = "/all-system_reliabilities", method = RequestMethod.GET)
    public String viewSytemReliabilityHomePage(Model model, HttpSession session) {
        session.setAttribute("freshLoad", true);
        return getPaginated(1, model, null, session);
    }

    @GetMapping("/show-new-system_reliability")
    public String showNewSystemReliability(Model model) {

        SystemReliability reliability = new SystemReliability();


        model.addAttribute("reliability", reliability);


        return "system_reliability/new_system_reliability";
    }

    @GetMapping("/show-update-system_reliability-form/{id}")
    public String showUpdateSystemReliabilityForm(@PathVariable(value = "id") Long id, Model model) {
        SystemReliability SystemReliability = service.getSystemReliabilityById(id);
//        List<SystemReliability> systemReliabilities = repository.findAll();

        model.addAttribute("rel", SystemReliability);
//        model.addAttribute("listOfSystemReliability", systemReliabilities);

        return "system_reliability/update_system_reliability";
    }

    @GetMapping("/show-systemReliability-details/{id}")
    public String showAircraftDetails(@PathVariable(value = "id") Long id, Model model) {
        SystemReliability SystemReliability = service.getSystemReliabilityById(id);
        List<SystemReliability> SystemReliabilities = repository.findAll();


        model.addAttribute("listOfWorkers", SystemReliabilities);

        model.addAttribute("details", SystemReliability);
        return "system_reliability/system_reliability_details";
    }

    @PostMapping("/save-system_reliability")
    public String saveSystemReliability(@ModelAttribute("systemReliability") SystemReliability systemReliability, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return "system_reliability/update_system_reliability";
        }

        service.saveSystemReliability(systemReliability);
        return "redirect:/all_system_reliabilities";
    }

    @GetMapping("/search-system_reliability")
    public String searchAircraft(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                 Model model) {
        List<SystemReliability> filteredPartRels = service.findSystemReliabilityByKeyWord(keyWord);
        Page<SystemReliability> page = new PageImpl<>(filteredPartRels); // Create a custom Page for the filtered data
        List<SystemReliability> listOfSystemRel = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfAirCrafts", listOfSystemRel);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword

        return "system_reliability/system_reliability";
    }


    @GetMapping("/delete-system_reliability/{id}")
    public String deleteSystemReliabilityById(@PathVariable(value = "id") Long id) {
        service.deleteSystemReliability(id);
        return "redirect:/all-system_reliabilities";
    }

    @GetMapping("/all-system_reliabilities/page/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") Integer pageNo, Model model,
                               @RequestParam(value = "keyWord", required = false) String keyWord, HttpSession session) {
        int pageSize = 5;
        Page<SystemReliability> page = service.findPaginated(pageNo, pageSize);

        List<SystemReliability> listOfSystemRel = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfSystemRel", listOfSystemRel);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword
        session.setAttribute("freshLoad", false); // Set to false after initial load

        return "system_reliability/system_reliability";
    }
}
