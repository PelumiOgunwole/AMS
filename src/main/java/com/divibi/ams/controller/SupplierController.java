package com.divibi.ams.controller;

import com.divibi.ams.model.Suppliers;
import com.divibi.ams.repository.SupplierRepository;
import com.divibi.ams.repository.WorkerRepository;
import com.divibi.ams.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/")
public class SupplierController {
        private  final  SupplierService supplierService;

        private final SupplierRepository supplierRepository;

        public SupplierController(SupplierService supplierService,
                                  SupplierRepository supplierRepository) {
            this.supplierService = supplierService;
            this.supplierRepository = supplierRepository;
        }

        @RequestMapping(value = "/all-suppliers",method = RequestMethod.GET  )
        public String viewAircraftHomePage (Model model, HttpSession session) {
            session.setAttribute("freshLoad", true);
            return getPaginated(1,model,null,session);
        }

        @GetMapping("/show-new-supplier")
        public String showNewSupplierForm(Model model) {
            Suppliers supplier = new Suppliers();
            model.addAttribute("supplier",supplier);
            return "supplier/new_supplier";
        }
        @GetMapping("/show-supplier-details/{id}")
        public String showAircraftDetails(@PathVariable(value = "id") Long id, Model model) {
            Suppliers supplier = supplierService.getSupplierById(id);
//        System.out.println(aircraft.getAircraftId());
//        System.out.println(aircraft.getTailNumber());
            model.addAttribute("details",supplier);
            return "supplier/supplier_details";
        }

        @GetMapping("/search-suppliers")
        public String searchWorkers(@RequestParam("keyword") String keyword,
                                    @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, Model model) {
            List<Suppliers> filteredSuppliers = supplierService.findSupplierByKeyWord(keyword);
            Page<Suppliers> page = new PageImpl<>(filteredSuppliers); // Create a custom Page for the filtered data
            List<Suppliers> listOfSupplier = page.getContent();

            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPage", page.getTotalPages());
            model.addAttribute("totalContent", page.getTotalElements());
            model.addAttribute("listOfSupplier", listOfSupplier);
            model.addAttribute("keyword", keyword); // Retain the search keyword
            return "supplier/supplier";
        }



        @PostMapping("/save-supplier-details")
        public String saveSupplierDetails(@ModelAttribute("supplier") Suppliers supplier) {
            supplierService.saveSupplier(supplier);
            return "redirect:/all-suppliers";
        }
        @GetMapping("/show-update-supplier-form/{id}")
        public String showUpdateSupplierForm(@PathVariable (value = "id") Long id, Model model) {
            Suppliers supplier = supplierService.getSupplierById(id);
            model.addAttribute("supplier",supplier);
//        String workerDetails = "update_worker-details.html";
            return "supplier/update_supplier";
        }
        @GetMapping("/delete-supplier/{id}")
        public String deleteWorkerById(@PathVariable (value = "id") Long id) {

            supplierService.deleteSupplier(id);
            return "redirect:/all-suppliers";
        }

        @GetMapping("/pages/{pageNos}")
        public String getPaginated(@PathVariable (value = "pageNos") Integer pageNos,Model model,
                                   @RequestParam(value = "keyword", required = false) String keyword,HttpSession session) {
            int pageSize =10;
            Page<Suppliers> page =supplierService.findPaginated(pageNos,pageSize);
            List<Suppliers> listOfSupplier = page.getContent();
            model.addAttribute("currentPage",pageNos);
            model.addAttribute("totalPage",page.getTotalPages());
            model.addAttribute("totalContent",page.getTotalElements());
            model.addAttribute("listOfSupplier",listOfSupplier);
            model.addAttribute("keyword", keyword); // Retain the search keyword
            session.setAttribute("freshLoad", false); // Set to false after initial load

            return "supplier/supplier";
        }



}
