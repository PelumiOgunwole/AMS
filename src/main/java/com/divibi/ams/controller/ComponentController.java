package com.divibi.ams.controller;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import com.divibi.ams.service.ComponentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @RequestMapping(value = "/components",method = RequestMethod.GET  )
    public String viewComponentHomePage (Model model) {
        return getComponentPaginated(1,model);
    }

    @GetMapping("/show-new-component")
    public String showNewComponent(Model model) {
        Component component = new Component();
        model.addAttribute("component",component);
        String comp = "new_component.html";
        return "new_component";
    }
    @PostMapping("/save-component")
    public String saveComponent(@ModelAttribute("component") Component component) {
        componentService.saveComponent(component);
        return "redirect:/components";
    }
    @GetMapping("/show-update-component-form/{id}")
    public String showUpdateComponentForm(@PathVariable (value = "id") Integer id, Model model) {
        Component component = componentService.getComponentById(id);
        model.addAttribute("component",component);
        return "update_component";
    }
    @GetMapping("/delete-component/{id}")
    public String deleteComponenttById(@PathVariable (value = "id") Integer id) {

        componentService.deleteComponent(id);
        return "redirect:/components";
    }

    @GetMapping("/page/{pageNumber}")
    public String getComponentPaginated(@PathVariable (value = "pageNumber") Integer pageNumber,Model model) {
        int pageSize =10;
        Page<Component> page =componentService.findPaginated(pageNumber,pageSize);
        List<Component> listOfComponents = page.getContent();
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("totalContent",page.getTotalElements());
        model.addAttribute("listOfComponents",listOfComponents);

        return "component";
    }
}
