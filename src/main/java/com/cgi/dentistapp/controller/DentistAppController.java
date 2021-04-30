package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO) {
        return "form";
    }

    @GetMapping("/dentistVisits")
    private List<DentistVisitEntity> getAllVisits() {
        return dentistVisitService.getAllVisits();
    }

    @GetMapping("/dentistVisits/{id}")
    private DentistVisitEntity getVisit(@PathVariable("id") int id) {
        return dentistVisitService.getVisitById(id);
    }

    @DeleteMapping("/dentistVisits/{id}")
    private void deleteVisit(@PathVariable("id") int id) {
        dentistVisitService.delete(id);
    }

    @PostMapping("/dentistVisits")
    @ResponseBody
    private int saveVisit(@RequestBody DentistVisitEntity visit) {
        dentistVisitService.saveOrUpdate(visit);
        return visit.getId();
    }

    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitTime(), dentistVisitDTO.getVisitClock());
        return "redirect:/results";
    }

    @GetMapping("/table")
    public String listVisits(Model model){
        model.addAttribute("visits", dentistVisitService.getAllVisits());
        return "table";
    }


}
