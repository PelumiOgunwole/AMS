package com.divibi.ams.controller;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.WorkOrder;
import com.divibi.ams.service.WorkOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

public class WorkOrderController {

    private WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @RequestMapping(value = "/all-work-order", method = RequestMethod.GET)
    public String viewworkOrderHomePage(Model model, HttpSession session) {
        session.setAttribute("freshLoad", true);
        return getPaginated(1, model, null, session);
    }

    @GetMapping("/show-new-work_order")
    public String showNewWorkOrder(Model model) {
        WorkOrder workOrder = new WorkOrder();
        model.addAttribute("aircraft", workOrder);
        return "work_orders/new_work_order";
    }

    @GetMapping("/show-work-order-details/{id}")
    public String showWorkOrderDetails(@PathVariable(value = "id") Long id, Model model) {
        WorkOrder workOrder = workOrderService.getWorkOrderById(id);
//        System.out.println(aircraft.getAircraftId());
//        System.out.println(aircraft.getTailNumber());
        model.addAttribute("details", workOrder);
        return "work_orders/work_order_details";
    }

    @PostMapping("/save-work_order")
    public String saveAircraft(@ModelAttribute("workOrder") WorkOrder workOrder, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return "work_order/update_work_order";
        }
        workOrderService.saveWorkOrder(workOrder);
        return "redirect:/all-work-order";
    }

    @GetMapping("/search-aircraft")
    public String searchWorkOrder(@RequestParam("keyWord") String keyWord,
                                 @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                 Model model) {
        List<WorkOrder> filteredWorkorder = workOrderService.findWorkOrderByKeyWord(keyWord);
        Page<WorkOrder> page = new PageImpl<>(filteredWorkorder); // Create a custom Page for the filtered data
        List<WorkOrder> listOfWorkOrder = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfWorkOrder", listOfWorkOrder);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword

        return "work_orders/work_order";
    }

    @GetMapping("/show-update-workorder-form/{id}")
    public String showUpdateAircraftForm(@PathVariable(value = "id") Long id, Model model) {
        WorkOrder workOrder = workOrderService.getWorkOrderById(id);
        model.addAttribute("aircraft", workOrder);
        return "work_orders/update_work_order";
    }

    @GetMapping("/delete-aircraft/{id}")
    public String deleteWorkOrderById(@PathVariable(value = "id") Long id) {
        workOrderService.deleteWorkOrder(id);
        return "redirect:/all-work-order";
    }

    @GetMapping("/page/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") Integer pageNo, Model model,
                               @RequestParam(value = "keyWord", required = false) String keyWord, HttpSession session) {
        int pageSize = 10;
        Page<WorkOrder> page = workOrderService.findPaginated(pageNo, pageSize);
        System.out.println("home");
        List<WorkOrder> listOfWorkOrder = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalContent", page.getTotalElements());
        model.addAttribute("listOfWorkOrder", listOfWorkOrder);
        model.addAttribute("keyWord", keyWord); // Retain the search keyword
        session.setAttribute("freshLoad", false); // Set to false after initial load
        String workOrder = "work_order.html";
        return workOrder;
    }
}
