package com.zyke.docdiag.controller;

import com.zyke.docdiag.model.Diagnosis;
import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.repository.DiagnosisRepository;
import com.zyke.docdiag.service.DiagnosisService;
import com.zyke.docdiag.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiagnosisController {

    @Autowired
    DiagnosisService diagnosisService;

    @Autowired
    DoctorService doctorService;

    @GetMapping("/home")
    public String home() {
        return "welcome";
    }

    @GetMapping("/diagnoses")
    public String showDiagnosis(ModelMap model) {
        model.put("diagnoses", diagnosisService.findAll());
        return "diagnoses-table";
    }

    @GetMapping("/add-diagnosis")
    public String showAdd(ModelMap model) {
        model.addAttribute("diagnosis", new Diagnosis(0, "", null, null));
        model.addAttribute("doctors", doctorService.findAll());
        return "diagnosis";
    }

    @GetMapping("/update-diagnosis/{id}")
    public String showUpdate(ModelMap model, @PathVariable long id) {
        model.put("doctor", doctorService.findById(id));
        return "doctor";
    }

    @PostMapping("/add-diagnosis")
    public String add(@ModelAttribute("diagnosis") Diagnosis d, BindingResult result) {
        if(result.hasErrors()) {
            return "add-diagnosis";
        }
        diagnosisService.add(d);
        return "redirect:/diagnoses";
    }

    @PostMapping("/update-diagnosis/{id}")
    public String update(@ModelAttribute("diagnosis") Diagnosis d, BindingResult result) {
        if(result.hasErrors()) {
            return "diagnosis";
        }
        diagnosisService.update(d);
        return "redirect:/diagnoses";
    }

    @GetMapping("/delete-diagnosis/{id}")
    public String delete(ModelMap model, @PathVariable long id) {
        diagnosisService.deleteById(id);
        return "redirect:/diagnoses";
    }
}
