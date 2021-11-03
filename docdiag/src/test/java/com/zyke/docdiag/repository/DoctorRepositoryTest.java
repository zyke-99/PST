package com.zyke.docdiag.repository;

import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.model.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;

    @Test
    void Save_ProvidedDoctorObject_CorrectDataSaved() {

        Doctor d = new Doctor("Doctor", "555");
        long savedId = doctorRepository.save(d).getId();
        Doctor savedDoctor = doctorRepository.findById(savedId).get();

        assertNotNull(savedDoctor);
        assertAll("Test save",
                () -> assertEquals(d.getId(), savedDoctor.getId()),
                () -> assertEquals(d.getName(), savedDoctor.getName()),
                () -> assertEquals(d.getPhoneNumber(), savedDoctor.getPhoneNumber()));
    }

    @Test
    void Save_UpdatedExistingDoctor_CorrectDataUpdated() {
        Doctor d = new Doctor("Doctor", "555");
        Doctor savedDoctor = doctorRepository.save(d);
        savedDoctor.setName("Another Doctor");
        Doctor updatedDoctor = doctorRepository.save(savedDoctor);
        assertAll("Test save update",
                () -> assertEquals(savedDoctor.getId(), updatedDoctor.getId()),
                () -> assertEquals(savedDoctor.getName(), updatedDoctor.getName()),
                () -> assertEquals(savedDoctor.getPhoneNumber(), updatedDoctor.getPhoneNumber())
        );
    }

    @Test
    void FindById_ProvidedExistingDoctorId_DoctorFound() {
        Doctor d = new Doctor("Doctor", "555");
        long savedId = doctorRepository.save(d).getId();
        assertNotNull(doctorRepository.findById(savedId));
    }

    @Test
    void FindAll_SearchingNonEmptyDatasource_ReturnsAllDiagnoses() {
        Doctor d1 = new Doctor("firstDoctor", "555");
        Doctor d2 = new Doctor("secondDoctor", "555");
        Doctor d3 = new Doctor("thirdDoctor", "555");
        doctorRepository.save(d1);
        doctorRepository.save(d2);
        doctorRepository.save(d3);
        List<Doctor> diagnoses = new ArrayList<Doctor>();
        doctorRepository.findAll().forEach(diagnoses::add);
        assertEquals(3, diagnoses.size());
    }

    @Test
    void DeleteById_ProvidedExistingDoctorId_DoctorWithIdNotFound() {
        Doctor d = new Doctor("Doctor", "555");
        long savedId = doctorRepository.save(d).getId();
        assertNotNull(doctorRepository.findById(savedId));
        doctorRepository.deleteById(savedId);
        assertFalse(doctorRepository.findById(savedId).isPresent());
    }

    @Test
    void DeleteAll_() {
        Doctor d1 = new Doctor("firstDoctor", "555");
        Doctor d2 = new Doctor("secondDoctor", "555");
        Doctor d3 = new Doctor("thirdDoctor", "555");
        doctorRepository.save(d1);
        doctorRepository.save(d2);
        doctorRepository.save(d3);
        List<Doctor> diagnoses1 = new ArrayList<Doctor>();
        doctorRepository.findAll().forEach(diagnoses1::add);
        assertEquals(3, diagnoses1.size());
        doctorRepository.deleteAll();
        List<Doctor> diagnoses2 = new ArrayList<Doctor>();
        doctorRepository.findAll().forEach(diagnoses2::add);
        assertEquals(0, diagnoses2.size());
    }

}
