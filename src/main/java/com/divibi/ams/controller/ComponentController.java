package com.divibi.ams.controller;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Component;
import com.divibi.ams.model.Worker;
import com.divibi.ams.repository.ComponentRepository;
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
    private final ComponentRepository componentRepository;

    public ComponentController(ComponentService componentService,
                               ComponentRepository componentRepository) {
        this.componentService = componentService;
        this.componentRepository = componentRepository;
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
    @GetMapping("/search-component")
    public ResponseEntity<String> searchComponents(@RequestParam("keyword") String keyword) {
        List<Component> filteredComponents = componentService.findComponentByKeyWord(keyword);
        String updatedTableContent = generateTableMarkup(filteredComponents);
        return ResponseEntity.ok(updatedTableContent);
    }

    private String generateTableMarkup(List<Component> workers) {
        StringBuilder tableMarkup = new StringBuilder();
        tableMarkup.append("<table>");

        // Add table rows
        for (Component worker : workers) {
            tableMarkup.append("<tr>");
            tableMarkup.append("<td>").append(worker.getComponentId()).append("</td>");
            tableMarkup.append("<td>").append(worker.getComponentName()).append("</td>");
            tableMarkup.append("<td>").append(worker.getManufacturer()).append("</td>");
            tableMarkup.append("<td>").append(worker.getStatus()).append("</td>");
            tableMarkup.append("<td>").append(worker.getFlightHours()).append("</td>");
            tableMarkup.append("<td><a th:href=\"@{'/show-new-component/' + ${worker.componentId}}\" class=\"btn btn-dark\"><i class=\"fa fa-pencil-square-o\" aria-hidden=\"true\"></i></a></td>");
            tableMarkup.append("<td><a th:href=\"@{'/delete-component/' + ${worker.componentId}}\" class=\"btn btn-danger\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a></td>");
            tableMarkup.append("</tr>");
        }

        tableMarkup.append("</tbody></table>");
        return tableMarkup.toString();
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
