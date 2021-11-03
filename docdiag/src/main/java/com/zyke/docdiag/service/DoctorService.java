package com.zyke.docdiag.service;

import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.repository.DiagnosisRepository;
import com.zyke.docdiag.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DiagnosisRepository diagnosisRepository;

    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public Doctor findById(long id) {
        return doctorRepository.findById(id).get();
    }

    @Transactional
    public void deleteById(long id) {
        diagnosisRepository.deleteAllByDoctorId(id);
        doctorRepository.deleteById(id);
    }

    @Transactional
    public void delete(Doctor d) {
        diagnosisRepository.deleteAllByDoctorId(d.getId());
        doctorRepository.delete(d);
    }

    public Doctor add(Doctor d) {
        return doctorRepository.save(d);
    }

    public void update(Doctor d) {
        doctorRepository.save(d);
    }
}
