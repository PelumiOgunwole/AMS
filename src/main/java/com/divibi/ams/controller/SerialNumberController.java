package com.divibi.ams.controller;

import com.divibi.ams.model.Aircraft;
import com.divibi.ams.model.SerialNumber;
import com.divibi.ams.model.Worker;
import com.divibi.ams.service.SerialNumberService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/serial_numbers")
public class SerialNumberController {

    private final SerialNumberService serialNumberService;

    public SerialNumberController(SerialNumberService serialNumberService) {
        this.serialNumberService = serialNumberService;
    }

    @RequestMapping(value = "/all-serials",method = RequestMethod.GET  )
    public String viewAircraftHomePage (Model model) {
        return getPaginated(1,model);
    }

    @GetMapping("/show-new-serial-orm")
    public String showNewSerial(Model model) {
        SerialNumber serialNumber = new SerialNumber();
        model.addAttribute("serial_no",serialNumber);
        return "new_serial";
    }
    @PostMapping("/save-serial")
    public String saveSerial(@ModelAttribute("serial") SerialNumber serialNumber) {
        serialNumberService.saveSerialNumber(serialNumber);
        return "redirect:/all-serials";
    }
    @GetMapping("/show-update-serial-form/{id}")
    public String showUpdateSerialForm(@PathVariable (value = "id") Integer id, Model model) {
        SerialNumber serialNumber = serialNumberService.getSerialNumberById(id);
        model.addAttribute("serial",serialNumber);
        return "update_serial";
    }
    @GetMapping("/delete-serial/{id}")
    public String deleteSerialById(@PathVariable (value = "id") Integer id) {

        serialNumberService.deleteSerialNumber(id);
        return "redirect:/all-serials";
    }

    @GetMapping("/page/{pageNo}")
    public String getPaginated(@PathVariable (value = "pageNo") Integer pageNo,Model model) {
        int pageSize =10;
        Page<SerialNumber> page =serialNumberService.findPaginated(pageNo,pageSize);
        List<SerialNumber> listOfSerials = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage",page.getTotalPages());
        model.addAttribute("totalContent",page.getTotalElements());
        model.addAttribute("listOfSerials",listOfSerials);

        return "index";
    }
}
