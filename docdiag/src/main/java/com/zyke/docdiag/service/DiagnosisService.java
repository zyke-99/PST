package com.zyke.docdiag.service;

import com.zyke.docdiag.model.Diagnosis;
import com.zyke.docdiag.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    @Autowired
    DiagnosisRepository diagnosisRepository;

    public List<Diagnosis> findAll() {
        return (List<Diagnosis>) diagnosisRepository.findAll();
    }

    public Diagnosis findById(long id) {
        return diagnosisRepository.findById(id).get();
    }

    public void deleteById(long id) {
        diagnosisRepository.deleteById(id);
    }

    public void delete(Diagnosis d) {
        diagnosisRepository.delete(d);
    }

    public Diagnosis add(Diagnosis d) {
        return diagnosisRepository.save(d);
    }

    public void update(Diagnosis d) {
        diagnosisRepository.save(d);
    }

}
