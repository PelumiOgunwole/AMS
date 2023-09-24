package com.divibi.ams.controller;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import com.divibi.ams.repository.ComponentRepository;
import com.divibi.ams.service.ComponentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class ComponentController {

    private final ComponentService componentService;
    private final ComponentRepository componentRepository;

    public ComponentController(ComponentService componentService,
                               ComponentRepository componentRepository) {
        this.componentService = componentService;
        this.componentRepository = componentRepository;
    }

    @RequestMapping(value = "/components",method = RequestMethod.GET  )
    public String viewComponentHomePage (Model model, HttpSession session) {
        session.setAttribute("freshLoad", true);
        return getComponentPaginated(1,model,null,session);
    }

    @GetMapping("/show-new-component")
    public String showNewComponent(Model model) {
        Component component = new Component();
        model.addAttribute("component",component);
        return "components/new_component";
    }
    @GetMapping("/show-component-details/{id}")
    public String showComponentDetails(@PathVariable (value = "id") Long id, Model model) {
        Component component = componentService.getComponentById(id);
//        System.out.println(component.getComponentId());
//        System.out.println(aircraft.getTailNumber());
        model.addAttribute("details",component);
        return "components/component_details";
    }
    @GetMapping("/search-component")
    public String searchComponents(@RequestParam("keyword") String keyword,
                                                   @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                   Model model) {
        List<Component> filteredComponents = componentService.findComponentByKeyWord(keyword);
        Page<Component> page = new PageImpl<>(filteredComponents); // Create a custom Page for the filtered data
        List<Component> listOfComponents = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfComponents", listOfComponents);
        model.addAttribute("keyword", keyword); // Retain the search keyword

        return "components/component";
    }


    @PostMapping("/save-component")
    public String saveComponent(@ModelAttribute("component") Component component) {
        componentService.saveComponent(component);
        return "redirect:/components";
    }
    @GetMapping("/show-update-component-form/{id}")
    public String showUpdateComponentForm(@PathVariable (value = "id") Long id, Model model) {
        Component component = componentService.getComponentById(id);
        model.addAttribute("component",component);
        return "components/update_component";
    }
    @GetMapping("/delete-component/{id}")
    public String deleteComponenttById(@PathVariable (value = "id") Long id) {

        componentService.deleteComponent(id);
        return "redirect:/components";
    }

    @GetMapping("/page/{pageNumber}")
    public String getComponentPaginated(@PathVariable (value = "pageNumber") Integer pageNumber,
        Model model,@RequestParam(value = "keyword", required = false) String keyword,HttpSession session) {
        int pageSize =10;
        Page<Component> page =componentService.findPaginated(pageNumber,pageSize);
        List<Component> listOfComponents = page.getContent();
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("totalContent",page.getTotalElements());
        model.addAttribute("listOfComponents",listOfComponents);
        model.addAttribute("keyword", keyword); // Retain the search keyword
        session.setAttribute("freshLoad", false); // Set to fals
        return "components/component";
    }
}
