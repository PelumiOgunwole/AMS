package com.divibi.ams.controller;

import com.divibi.ams.model.Worker;
import com.divibi.ams.repository.WorkerRepository;
import com.divibi.ams.service.WorkerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public String viewWorkerHomePage (Model model, HttpSession session) {
        session.setAttribute("freshLoad", true);
        return getPaginated(1,model,null,session);
    }

    @GetMapping("/show-new-worker")
    public String showNewWorkerForm(Model model) {
        Worker worker = new Worker();
        model.addAttribute("worker",worker);
        return "worker/new_worker";
    }
    @GetMapping("/show-worker-details/{id}")
    public String showAircraftDetails(@PathVariable (value = "id") Long id, Model model) {
        Worker worker = workerService.getWorkerById(id);
//        System.out.println(aircraft.getAircraftId());
//        System.out.println(aircraft.getTailNumber());
        model.addAttribute("details",worker);
        return "worker/worker_details";
    }

    @GetMapping("/search-worker")
    public String searchWorkers(@RequestParam("keyword") String keyword,
    @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
        List<Worker> filteredWorkers = workerService.findWorkerByKeyWord(keyword);
        Page<Worker> page = new PageImpl<>(filteredWorkers); // Create a custom Page for the filtered data
        List<Worker> listOfWorker = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfWorker", listOfWorker);
        model.addAttribute("keyword", keyword); // Retain the search keyword
        return "worker/worker";
    }



    @PostMapping("/save-worker-details")
    public String saveWorkerDetails(@ModelAttribute("worker") Worker worker) {
        workerService.saveWorker(worker);
        return "redirect:/all-workers";
    }
    @GetMapping("/show-update-worker-form/{id}")
    public String showUpdateWorkerForm(@PathVariable (value = "id") Long id, Model model) {
        Worker worker = workerService.getWorkerById(id);
        model.addAttribute("worker",worker);
//        String workerDetails = "update_worker-details.html";
        return "worker/update_worker";
    }
    @GetMapping("/delete-worker/{id}")
    public String deleteWorkerById(@PathVariable (value = "id") Long id) {

        workerService.deleteWorker(id);
        return "redirect:/all-workers";
    }

    @GetMapping("/all-workers/pages/{pageNom}")
    public String getPaginated(@PathVariable (value = "pageNom") Integer pageNo,Model model,
                               @RequestParam(value = "keyword", required = false) String keyword,HttpSession session) {
        int pageSize =5;
        Page<Worker> page =workerService.findPaginated(pageNo,pageSize);
        List<Worker> listOfWorkers = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("totalContent",page.getTotalElements());
        model.addAttribute("listOfWorker",listOfWorkers);
//        model.addAttribute("totalItems", totalItems);
        model.addAttribute("keyword", keyword); // Retain the search keyword
        session.setAttribute("freshLoad", false); // Set to false after initial load

        return "worker/worker";
    }

}
