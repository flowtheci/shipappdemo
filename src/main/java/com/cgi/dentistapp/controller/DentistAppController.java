package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.dto.RemoveVisit;
import com.cgi.dentistapp.dto.SearchForVisit;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javax.validation.Valid;


@Controller
@EnableAutoConfiguration
@SessionAttributes({"dentists","dentistVisitDTO","selectedVisit"})

public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    // Maps GET requests for main domain and adds attributes to model
    @GetMapping("/")
    public String showRegisterForm(Model model) {
        model.addAttribute("dentistVisitDTO", new DentistVisitDTO());
        model.addAttribute("dentists", dentistVisitService.getDentistList());
        model.addAttribute("availability", dentistVisitService.getAvailabilityOfLast());
        return "form";
    }

    @PostMapping(path="/", name="submit")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {return "form";}

       if(dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitTime(),
               dentistVisitDTO.getVisitClock(), dentistVisitDTO.getCustomerName()))
       {
           return "redirect:/results";
       }
       else return "form";
    }

    @GetMapping("/table")
    public String listVisits(Model model){
        model.addAttribute("visits", dentistVisitService.getAllVisits());
        model.addAttribute("removeVisit", new RemoveVisit());
        model.addAttribute("searchForVisit", new SearchForVisit());
        model.addAttribute("searching", dentistVisitService.getIsSearching());
        dentistVisitService.resetSearch();
        return "table";
    }

    @PostMapping(path="/table", params="remove")
    public String removeVisit(RemoveVisit removeVisit){
        dentistVisitService.delete(removeVisit.getId());
        return "redirect:/table";
    }

    @PostMapping(path="/table", params="modify")
    public String changeVisit(RemoveVisit removeVisit){
        dentistVisitService.modify(removeVisit.getId());
        return "redirect:/modify";
    }

    @PostMapping(path="/table", params="search")
    public String searchForVisit(SearchForVisit searchForVisit){
        dentistVisitService.searchForVisit(searchForVisit.getSearchTerm());
        return "redirect:/table";
    }

    @GetMapping("/modify")
    public String showModifyForm(Model model) {
        model.addAttribute("selectedVisit", dentistVisitService.getSelectedVisit());
        model.addAttribute("dentistVisitDTO", new DentistVisitDTO());
        model.addAttribute("dentists", dentistVisitService.getDentistList());
        return "modify";
    }

    @PostMapping(path="/modify", params="change")
    public String postModifyForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "modify";
        }

        dentistVisitService.changeVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitTime(),
                                        dentistVisitDTO.getVisitClock(), dentistVisitDTO.getCustomerName());
        return "redirect:/table";
    }

}
