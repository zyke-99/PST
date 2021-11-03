package com.zyke.docdiag.controller;

import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public String showDoctors(ModelMap model) {
        model.put("doctors", doctorService.findAll());
        return "doctors-table";
    }

    @GetMapping("/add-doctor")
    public String showAdd(ModelMap model) {
        model.put("doctor", new Doctor("", ""));
        return "doctor";
    }

    @GetMapping("/update-doctor/{id}")
    public String showUpdate(ModelMap model, @PathVariable long id) {
        model.put("doctor", doctorService.findById(id));
        return "doctor";
    }

    @PostMapping("/add-doctor")
    public String add(@ModelAttribute("doctor") Doctor d, BindingResult result) {

        if(result.hasErrors()) {
            return "doctor";
        }

        doctorService.add(d);
        return "redirect:/doctors";

    }

    @PostMapping("/update-doctor/{id}")
    public String update(ModelMap model, @ModelAttribute("doctor") Doctor d, BindingResult result) {

        if(result.hasErrors()) {
            return "doctor";
        }
        doctorService.update(d);
        return "redirect:/doctors";
    }

    @GetMapping("/delete-doctor/{id}")
    public String delete(ModelMap model, @PathVariable long id) {
        doctorService.deleteById(id);
        return "redirect:/doctors";
    }
}
