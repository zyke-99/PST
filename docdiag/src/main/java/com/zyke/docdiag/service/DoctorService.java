package com.zyke.docdiag.service;

import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public Doctor findById(long id) {
        return doctorRepository.findById(id).get();
    }

    public void deleteById(long id) {
        doctorRepository.deleteById(id);
    }

    public void delete(Doctor d) {
        doctorRepository.delete(d);
    }

    public Doctor add(Doctor d) {
        return doctorRepository.save(d);
    }

    public void update(Doctor d) {
        doctorRepository.save(d);
    }
}
