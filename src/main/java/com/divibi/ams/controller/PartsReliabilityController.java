package com.divibi.ams.controller;

import com.divibi.ams.model.*;
import com.divibi.ams.repository.PartsReliabilityRepository;
import com.divibi.ams.service.PartsReliabilityService;
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
import java.util.List;
@Controller
@RequestMapping("/pd")
public class PartsReliabilityController {

    @Autowired
    private PartsReliabilityService service;
    @Autowired
    private PartsReliabilityRepository repository;

    @RequestMapping(value = "/all-parts_reliabilities",method = RequestMethod.GET  )
    public String viewPartReliabilityHomePage (Model model, HttpSession session) {
        session.setAttribute("freshLoad", true);
        return getPaginated(1,model,null,session);
    }

    @GetMapping("/show-new-parts-reliability")
    public String showNewPartsreliability(Model model) {

        PartsReliability reliability = new PartsReliability();


        model.addAttribute("reliability",reliability);


        return "parts_reliability/new_parts_reliability";
    }

    @GetMapping("/show-update-parts_reliability-form/{id}")
    public String showUpdatePartsReliabilityForm(@PathVariable(value = "id") Long id, Model model) {
        PartsReliability partsReliability = service.getPartsReliabilityById(id);
        List<PartsReliability> partsReliabilities = repository.findAll();

        model.addAttribute("rel",partsReliability);
        model.addAttribute("listOfPartsReliability",partsReliabilities);

        return "parts_reliability/update_parts_reliability";
    }
    @GetMapping("/show-partReliabilities-details/{id}")
    public String showAircraftDetails(@PathVariable (value = "id") Long id, Model model) {
        PartsReliability partsReliability = service.getPartsReliabilityById(id);
        List<PartsReliability> partsReliabilities = repository.findAll();


        model.addAttribute("listOfWorkers",partsReliabilities);

        model.addAttribute("details",partsReliability);
        return "parts_reliability/parts_reliability_details";
    }

    @PostMapping("/save-parts-reliability")
    public String savePartsReliability(@ModelAttribute("partsReliability") PartsReliability partsReliability, BindingResult bindingResult) throws ParseException {
        if(bindingResult.hasErrors()){
            return "parts_reliability/update_parts_reliability";
        }

        service.savePartsReliability(partsReliability);
        return "redirect:/all_parts_reliabilities";
    }
    @GetMapping("/search-parts-reliability")
    public String searchAircraft(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                 Model model) {
        List<PartsReliability> filteredPartRels = service.findPartsReliabilityByKeyWord(keyWord);
        Page<PartsReliability> page = new PageImpl<>(filteredPartRels); // Create a custom Page for the filtered data
        List<PartsReliability> listOfPartsRel = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfAirCrafts", listOfPartsRel);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword

        return "parts_reliability/parts_reliability";
    }



    @GetMapping("/delete-parts-reliability/{id}")
    public String deletePartReliabilityById(@PathVariable (value = "id") Long id) {
        service.deletePartsReliability(id);
        return "redirect:/all-parts-reliabilities";
    }

    @GetMapping("/all-parts-reliabilities/page/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") Integer pageNo, Model model,
                               @RequestParam(value = "keyWord", required = false) String keyWord,HttpSession session) {
        int pageSize = 5;
        Page<PartsReliability> page = service.findPaginated(pageNo, pageSize);

        List<PartsReliability> listOfPartsRel = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfPartsRel", listOfPartsRel);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword
        session.setAttribute("freshLoad", false); // Set to false after initial load

        return "parts_reliability/parts_reliability";
    }


}
