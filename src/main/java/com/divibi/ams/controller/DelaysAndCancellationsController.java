package com.divibi.ams.controller;

import com.divibi.ams.model.DelaysAndCancellations;
import com.divibi.ams.repository.DelaysAndCancellationsRepository;
import com.divibi.ams.service.DelaysAndCancellationsService;
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
public class DelaysAndCancellationsController {

    @Autowired
    private DelaysAndCancellationsService service;
    @Autowired
    private DelaysAndCancellationsRepository repository;


    @RequestMapping(value = "/all_delays_and_cancellations", method = RequestMethod.GET)
    public String viewDelaysAndCancellationsHomePage(Model model, HttpSession session) {
        session.setAttribute("freshLoad", true);
        return getPaginated(1, model, null, session);
    }

    @GetMapping("/show_new-delays_and_cancellations")
    public String showNewDelaysAndCancellations(Model model) {

        DelaysAndCancellations cancellations = new DelaysAndCancellations();


        model.addAttribute("cancellations", cancellations);


        return "dels&canc/new_delays_and_cancellations";
    }

    @GetMapping("/show_update_delays_and_cancellations-form/{id}")
    public String showUpdateDelaysAndCancellationsForm(@PathVariable(value = "id") Long id, Model model) {
        DelaysAndCancellations DelaysAndCancellations = service.getDelaysAndCancellationsById(id);
//        List<DelaysAndCancellations> DelaysAndCancellations = cancellations.findAll();

        model.addAttribute("delay", DelaysAndCancellations);
//        model.addAttribute("listOfDelaysAndCancellationsr", DelaysAndCancellations);

        return "dels&canc/update_delays_and_cancellations";
    }

    @GetMapping("/show_delays_and_cancellations-details/{id}")
    public String showAircraftDetails(@PathVariable(value = "id") Long id, Model model) {
        DelaysAndCancellations DelaysAndCancellations = service.getDelaysAndCancellationsById(id);


        model.addAttribute("details", DelaysAndCancellations);
        return "dels&canc/delays_and_cancellations_details";
    }

    @PostMapping("/save_delays_and_cancellations")
    public String saveDelaysAndCancellations(@ModelAttribute("delaysAndCancellations") DelaysAndCancellations DelaysAndCancellations, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return "dels&canc/update_delays_and_cancellations";
        }

        DelaysAndCancellations delaysAndCancellations = new DelaysAndCancellations();
        service.saveDelaysAndCancellations(DelaysAndCancellations);
        return "redirect:/all_delays_and_cancellations";
    }

    @GetMapping("/search_delays_and_cancellations")
    public String searchAircraft(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                 Model model) {
        List<DelaysAndCancellations> filteredPartRels = service.findDelaysAndCancellationsByKeyWord(keyWord);
        Page<DelaysAndCancellations> page = new PageImpl<>(filteredPartRels); // Create a custom Page for the filtered data
        List<DelaysAndCancellations> cancellationsList= page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfAirCrafts", cancellationsList);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword

        return "dels&canc/search_delays_and_cancellations";
    }


    @GetMapping("/delete_delays_and_cancellations/{id}")
    public String deleteDelaysAndCancellationsById(@PathVariable(value = "id") Long id) {
        service.deleteDelaysAndCancellations(id);
        return "redirect:/all_delays_and_cancellations";
    }

    @GetMapping("/all_delays_and_cancellations/page/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") Integer pageNo, Model model,
                               @RequestParam(value = "keyWord", required = false) String keyWord, HttpSession session) {
        int pageSize = 5;
        Page<DelaysAndCancellations> page = service.findPaginated(pageNo, pageSize);

        List<DelaysAndCancellations> cancellationsList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("cancellationsList", cancellationsList);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword
        session.setAttribute("freshLoad", false); // Set to false after initial load

        return "dels&canc/delays_and_cancellations";
    }
}
