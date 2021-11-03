package com.zyke.docdiag.controller;

import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "${api-prefix}/doctor")
public class DoctorRestController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable long id) {
        return new ResponseEntity<>(doctorService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> addDoctor(@RequestBody Doctor doctor) {
        Doctor d = doctorService.add(doctor);
        if(d == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(d.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable long id) {
        doctorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
