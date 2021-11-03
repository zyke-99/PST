package com.zyke.docdiag.service;

import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.repository.DiagnosisRepository;
import com.zyke.docdiag.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    DoctorRepository doctorRepository;
    @Mock
    DiagnosisRepository diagnosisRepository;

    @InjectMocks
    DoctorService doctorService;

    @Test
    void FindAll_() {
        Doctor d1 = new Doctor("Doctor1", "555");
        Doctor d2 = new Doctor("Doctor2", "555");
        Doctor d3 = new Doctor("Doctor3", "555");
        List<Doctor> doctors = new LinkedList<>();
        doctors.add(d1);
        doctors.add(d2);
        doctors.add(d3);
        when(doctorRepository.findAll()).thenReturn(doctors);
        List<Doctor> allDoctors = doctorService.findAll();
        verify(doctorRepository).findAll();
        assertEquals(3, allDoctors.size());
    }

    @Test
    void FindById_() {
        Doctor d = new Doctor("Doctor", "555");
        when(doctorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(d));
        Doctor doctor = doctorService.findById(1);
        verify(doctorRepository).findById(Mockito.anyLong());
        assertNotNull(doctor);
    }

    @Test
    void DeleteById_() {
        doctorService.deleteById(1);
        verify(diagnosisRepository).deleteAllByDoctorId(Mockito.anyLong());
        verify(doctorRepository).deleteById(Mockito.anyLong());
    }

    @Test
    void Delete_ProvidedDoctorObject_CallsDeleteInDiagnosisAndDoctorRepository() {
        Doctor d = new Doctor("Doctor", "555");
        doctorService.delete(d);
        verify(diagnosisRepository).deleteAllByDoctorId(Mockito.anyLong());
        verify(doctorRepository).delete(Mockito.any(Doctor.class));
    }

    @Test
    void Add_ProvidedDoctorObject_CallsRepositorySaveMethodAndReturnsObject() {
        Doctor d = new Doctor("Doctor", "555");
        when(doctorRepository.save(Mockito.any(Doctor.class))).thenReturn(d);
        Doctor savedDoctor = doctorService.add(d);
        verify(doctorRepository).save(Mockito.any(Doctor.class));
        assertNotNull(savedDoctor);
    }

    @Test
    void Update_ProvidedDoctorObject_CallsRepositorySaveMethod() {
        Doctor d = new Doctor("Doctor", "555");
        doctorService.update(d);
        verify(doctorRepository).save(Mockito.any(Doctor.class));
    }

}
