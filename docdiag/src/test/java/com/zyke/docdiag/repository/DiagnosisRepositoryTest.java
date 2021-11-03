package com.zyke.docdiag.repository;

import com.zyke.docdiag.model.Diagnosis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DiagnosisRepositoryTest {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Test
    void Save_ProvidedDiagnosisObject_CorrectDataSaved() {

        Diagnosis d = new Diagnosis(6,"Illness", "2021-03-15", 1);
        long savedId = diagnosisRepository.save(d).getId();
        Diagnosis savedDiagnosis = diagnosisRepository.findById(savedId).get();

        assertNotNull(savedDiagnosis);
        assertAll("Test save",
                () -> assertEquals(d.getId(), savedDiagnosis.getId()),
                () -> assertEquals(d.getDiagnosisName(), savedDiagnosis.getDiagnosisName()),
                () -> assertEquals(d.getDate(), savedDiagnosis.getDate()),
                () -> assertEquals(d.getDoctorId(), savedDiagnosis.getDoctorId()),
                () -> assertEquals(d.getPatientId(), savedDiagnosis.getPatientId()));
    }

    @Test
    void Save_UpdatedExistingDiagnosis_CorrectDataUpdated() {
        Diagnosis d = new Diagnosis(0, "Illness", "2015-03-15", 1);
        Diagnosis savedDiagnosis = diagnosisRepository.save(d);
        savedDiagnosis.setDiagnosisName("Another Illness");
        Diagnosis updatedDiagnosis = diagnosisRepository.save(savedDiagnosis);
        assertAll("Test save update",
                () -> assertEquals(savedDiagnosis.getPatientId(), updatedDiagnosis.getPatientId()),
                () -> assertEquals(savedDiagnosis.getDiagnosisName(), updatedDiagnosis.getDiagnosisName()),
                () -> assertEquals(savedDiagnosis.getDate(), updatedDiagnosis.getDate()),
                () -> assertEquals(savedDiagnosis.getDoctorId(), updatedDiagnosis.getDoctorId()),
                () -> assertEquals(savedDiagnosis.getPatientId(), updatedDiagnosis.getPatientId())
        );
    }

    @Test
    void FindById_ProvidedExistingDiagnosisId_DiagnosisFound() {
        Diagnosis d = new Diagnosis(0, "Illness", "2015-03-15", 1);
        long savedId = diagnosisRepository.save(d).getId();
        assertNotNull(diagnosisRepository.findById(savedId));
    }

    @Test
    void FindAll_SearchingNonEmptyDatasource_ReturnsAllDiagnoses() {
        Diagnosis d1 = new Diagnosis(0, "Illness", "2015-03-15", 1);
        Diagnosis d2 = new Diagnosis(0, "Illness", "2015-03-15", 2);
        Diagnosis d3 = new Diagnosis(0, "Illness", "2015-03-15", 3);
        diagnosisRepository.save(d1);
        diagnosisRepository.save(d2);
        diagnosisRepository.save(d3);
        List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();
        diagnosisRepository.findAll().forEach(diagnoses::add);
        assertEquals(3, diagnoses.size());
    }

    @Test
    void DeleteById_ProvidedExistingDiagnosisId_DiagnosisWithIdNotFound() {
        Diagnosis d = new Diagnosis(0, "Illness", "2015-03-15", 1);
        long savedId = diagnosisRepository.save(d).getId();
        assertNotNull(diagnosisRepository.findById(savedId));
        diagnosisRepository.deleteById(savedId);
        assertFalse(diagnosisRepository.findById(savedId).isPresent());
    }

    @Test
    void DeleteAll_() {
        Diagnosis d1 = new Diagnosis(0, "Illness", "2015-03-15", 1);
        Diagnosis d2 = new Diagnosis(0, "Illness", "2015-03-15", 2);
        Diagnosis d3 = new Diagnosis(0, "Illness", "2015-03-15", 3);
        diagnosisRepository.save(d1);
        diagnosisRepository.save(d2);
        diagnosisRepository.save(d3);
        List<Diagnosis> diagnoses1 = new ArrayList<Diagnosis>();
        diagnosisRepository.findAll().forEach(diagnoses1::add);
        assertEquals(3, diagnoses1.size());
        diagnosisRepository.deleteAll();
        List<Diagnosis> diagnoses2 = new ArrayList<Diagnosis>();
        diagnosisRepository.findAll().forEach(diagnoses2::add);
        assertEquals(0, diagnoses2.size());
    }

}
