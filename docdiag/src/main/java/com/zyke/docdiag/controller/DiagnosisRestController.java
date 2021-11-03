package com.zyke.docdiag.controller;

import com.zyke.docdiag.model.Diagnosis;
import com.zyke.docdiag.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "${api-prefix}/diagnosis")
public class DiagnosisRestController {

    @Autowired
    DiagnosisService diagnosisService;

    @GetMapping("")
    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisService.findAll();
    }

    @GetMapping("/{id}")
    public Diagnosis getDiagnosisById(@PathVariable long id) {
        return diagnosisService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Void> addDiagnosis(@RequestBody Diagnosis diagnosis) {
        Diagnosis d = diagnosisService.add(diagnosis);
        if (d == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(d.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnosisById(@PathVariable long id) {
        diagnosisService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
