package com.divibi.ams.controller;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.Worker;
import com.divibi.ams.repository.WorkerRepository;
import com.divibi.ams.service.WorkerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class WorkerController {

    private final WorkerService workerService;
    private final WorkerRepository workerRepository;

    public WorkerController(WorkerService workerService,
                            WorkerRepository workerRepository) {
        this.workerService = workerService;
        this.workerRepository = workerRepository;
    }

    @RequestMapping(value = "/all-workers",method = RequestMethod.GET  )
    public String viewAircraftHomePage (Model model) {
        return getPaginated(1,model);
    }

    @GetMapping("/show-new-worker")
    public String showNewWorkerForm(Model model) {
        Worker worker = new Worker();
        model.addAttribute("worker",worker);
        return "new_worker";
    }

    @GetMapping("/search-worker")
public ResponseEntity<String> searchWorkers(@RequestParam("keyword") String keyword) {
    List<Worker> filteredWorkers = workerRepository.searchByKeyword(keyword);
    String updatedTableContent = generateTableMarkup(filteredWorkers);
    return ResponseEntity.ok(updatedTableContent);
}

    private String generateTableMarkup(List<Worker> workers) {
        StringBuilder tableMarkup = new StringBuilder();
        tableMarkup.append("<table>");

        // Add table rows
        for (Worker worker : workers) {
            tableMarkup.append("<tr>");
            tableMarkup.append("<td>").append(worker.getWorkerId()).append("</td>");
            tableMarkup.append("<td>").append(worker.getFirstName()).append("</td>");
            tableMarkup.append("<td>").append(worker.getLastName()).append("</td>");
            tableMarkup.append("<td>").append(worker.getJobTitle()).append("</td>");
            tableMarkup.append("<td><a th:href=\"@{'/show-update-worker-form/' + ${worker.workerId}}\" class=\"btn btn-dark\"><i class=\"fa fa-pencil-square-o\" aria-hidden=\"true\"></i></a></td>");
            tableMarkup.append("<td><a th:href=\"@{'/delete-worker/' + ${worker.workerId}}\" class=\"btn btn-danger\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a></td>");
            tableMarkup.append("</tr>");
        }

        tableMarkup.append("</tbody></table>");
        return tableMarkup.toString();
    }
    @PostMapping("/save-worker-details")
    public String saveWorkerDetails(@ModelAttribute("worker") Worker worker) {
        workerService.saveWorker(worker);
        return "redirect:/all-workers";
    }
    @GetMapping("/show-update-worker-form/{id}")
    public String showUpdateWorkerForm(@PathVariable (value = "id") Integer id, Model model) {
        Worker worker = workerService.getWorkerById(id);
        model.addAttribute("worker",worker);
        String workerDetails = "update_worker-details.html";
        return workerDetails;
    }
    @GetMapping("/delete-worker/{id}")
    public String deleteWorkerById(@PathVariable (value = "id") Integer id) {

        workerService.deleteWorker(id);
        return "redirect:/all-workers";
    }

    @GetMapping("/pages/{pageNo}")
    public String getPaginated(@PathVariable (value = "pageNo") Integer pageNo,Model model) {
        int pageSize =10;
        Page<Worker> page =workerService.findPaginated(pageNo,pageSize);
        List<Worker> listOfWorkers = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("totalContent",page.getTotalElements());
        model.addAttribute("listOfWorker",listOfWorkers);

        return "worker";
    }

}
