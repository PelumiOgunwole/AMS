//package com.divibi.ams.controller;
//
//import com.divibi.ams.model.Aircraft;
//import com.divibi.ams.model.MaintenanceRecord;
//import com.divibi.ams.model.Worker;
//import com.divibi.ams.service.MaintenanceRecordService;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/")
//public class MaintenanceRecordController {
//
//    private final MaintenanceRecordService maintenanceRecordService;
//
//    public MaintenanceRecordController(MaintenanceRecordService maintenanceRecordService) {
//        this.maintenanceRecordService = maintenanceRecordService;
//    }
//
//    @RequestMapping(value = "/all-maintenance",method = RequestMethod.GET  )
//    public String viewAircraftHomePage (Model model) {
//        return getMaintenancePaginated(1,model);
//    }
//
//    @GetMapping("/show-new-maintenance-form")
//    public String showNewAircraft(Model model) {
//        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
//        model.addAttribute("maintenance_record",maintenanceRecord);
//        return "new_maintenance";
//    }
//    @PostMapping("/save-maintenance")
//    public String saveAircraft(@ModelAttribute("maintenance") MaintenanceRecord maintenanceRecord) {
//        maintenanceRecordService.saveMaintenanceRecord(maintenanceRecord);
//        return "redirect:/all-maintenance";
//    }
//    @GetMapping("/search-maintenance")
//    public ResponseEntity<String> searchMaintenanceRecords(@RequestParam("keyword") String keyword) {
//        List<MaintenanceRecord> filteredWorkers = maintenanceRecordService.findMaintenanceByKeyWord(keyword);
//        String updatedTableContent = generateTableMarkup(filteredWorkers);
//        return ResponseEntity.ok(updatedTableContent);
//    }
//
//    private String generateTableMarkup(List<MaintenanceRecord> workers) {
//        StringBuilder tableMarkup = new StringBuilder();
//        tableMarkup.append("<table>");
//
//        // Add table rows
//        for (MaintenanceRecord worker : workers) {
//            tableMarkup.append("<tr>");
//            tableMarkup.append("<td>").append(worker.getMaintenanceId()).append("</td>");
//            tableMarkup.append("<td>").append(worker.getMaintenanceDate()).append("</td>");
//            tableMarkup.append("<td>").append(worker.getDescription()).append("</td>");
//            tableMarkup.append("<td>").append(worker.getComponentId()).append("</td>");
//            tableMarkup.append("<td><a th:href=\"@{'/show-update-maintenance-form/' + ${worker.workerId}}\" class=\"btn btn-dark\"><i class=\"fa fa-pencil-square-o\" aria-hidden=\"true\"></i></a></td>");
//            tableMarkup.append("<td><a th:href=\"@{'/delete-maintenance-record/' + ${worker.workerId}}\" class=\"btn btn-danger\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a></td>");
//            tableMarkup.append("</tr>");
//        }
//
//        tableMarkup.append("</tbody></table>");
//        return tableMarkup.toString();
//    }
//    @GetMapping("/show-update-maintenance-form/{id}")
//    public String showUpdateAircraftForm(@PathVariable (value = "id") Integer id, Model model) {
//        MaintenanceRecord maintenanceRecord = maintenanceRecordService.getMaintenanceRecordById(id);
//        model.addAttribute("maintenance_record",maintenanceRecord);
//        return "update_maintenance";
//    }
//    @GetMapping("/delete-maintenance-record/{id}")
//    public String deleteMaintenanceRecordById(@PathVariable (value = "id") Integer id) {
//
//        maintenanceRecordService.deleteMaintenanceRecord(id);
//        return "redirect:/all-maintenance";
//    }
//
//    @GetMapping("/page/{pageNom}")
//    public String getMaintenancePaginated(@PathVariable (value = "pageNom") Integer pageNom,Model model) {
//        int pageSize =10;
//        Page<MaintenanceRecord> page =maintenanceRecordService.findMaintenancePaginated(pageNom,pageSize);
//        List<MaintenanceRecord> listOfMaintenanceRecord = page.getContent();
//        model.addAttribute("currentPage",pageNom);
//        model.addAttribute("totalPage",page.getTotalPages());
//        model.addAttribute("totalContent",page.getTotalElements());
//        model.addAttribute("maintenanceRecords",listOfMaintenanceRecord);
//
//        return "maintenance";
//    }
//}
