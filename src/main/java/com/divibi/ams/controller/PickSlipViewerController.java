package com.divibi.ams.controller;

import com.divibi.ams.model.PickSlipViewer;
import com.divibi.ams.model.SystemReliability;
import com.divibi.ams.repository.PickSlipViewerRepository;
import com.divibi.ams.repository.SystemReliabilityRepository;
import com.divibi.ams.service.PickSlipViewerService;
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
public class PickSlipViewerController {

    @Autowired
    private PickSlipViewerService service;
    @Autowired
    private PickSlipViewerRepository repository;

    @RequestMapping(value = "/all-pick_slip_viewer", method = RequestMethod.GET)
    public String viewPickSlipViewerHomePage(Model model, HttpSession session) {
        session.setAttribute("freshLoad", true);
        return getPaginated(1, model, null, session);
    }

    @GetMapping("/show-new-pick_slip_viewer")
    public String showNewPickSlipViewer(Model model) {

        PickSlipViewer viewer = new PickSlipViewer();


        model.addAttribute("viewer", viewer);


        return "pick_slip_viewer/new_pick_slip_viewer";
    }

    @GetMapping("/show-update-pick_slip_viewer-form/{id}")
    public String showUpdatePickSlipViewerForm(@PathVariable(value = "id") Long id, Model model) {
        PickSlipViewer PickSlipViewer = service.getPickSlipViewerById(id);
//        List<PickSlipViewer> pickSlipViewer = viewer.findAll();

        model.addAttribute("rel", PickSlipViewer);
//        model.addAttribute("listOfPickSlipViewer", pickSlipViewer);

        return "pick_slip_viewer/update_pick_slip_viewer";
    }

    @GetMapping("/show-pick_slip_viewer-details/{id}")
    public String showAircraftDetails(@PathVariable(value = "id") Long id, Model model) {
        PickSlipViewer pickSlipViewer = service.getPickSlipViewerById(id);


        model.addAttribute("details", pickSlipViewer);
        return "pick_slip_viewer/pick_slip_viewer_details";
    }

    @PostMapping("/save_pick_slip_viewer")
    public String savePickSlipViewer(@ModelAttribute("pickSlipViewer") PickSlipViewer pickSlipViewer, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return "pick_slip_viewer/update_pick_slip_viewer";
        }

        PickSlipViewer PickSlipViewer = new PickSlipViewer();
        service.savePickSlipViewer(PickSlipViewer);
        return "redirect:/all_pick_slip_viewer";
    }

    @GetMapping("/search-pick_slip_viewer")
    public String searchAircraft(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                 Model model) {
        List<PickSlipViewer> filteredPartRels = service.findPickSlipViewerByKeyWord(keyWord);
        Page<PickSlipViewer> page = new PageImpl<>(filteredPartRels); // Create a custom Page for the filtered data
        List<PickSlipViewer> viewerList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfAirCrafts", viewerList);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword

        return "pick_slip_viewer/pick_slip_viewer";
    }


    @GetMapping("/delete-pick_slip_viewer/{id}")
    public String deletePickSlipViewerById(@PathVariable(value = "id") Long id) {
        service.deletePickSlipViewer(id);
        return "redirect:/all-pick_slip_viewer";
    }

    @GetMapping("/all-pick_slip_viewer/page/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") Integer pageNo, Model model,
                               @RequestParam(value = "keyWord", required = false) String keyWord, HttpSession session) {
        int pageSize = 5;
        Page<PickSlipViewer> page = service.findPaginated(pageNo, pageSize);

        List<PickSlipViewer> viewerList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("viewerList", viewerList);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword
        session.setAttribute("freshLoad", false); // Set to false after initial load

        return "pick_slip_viewer/pick_slip_viewer";
    }
}
