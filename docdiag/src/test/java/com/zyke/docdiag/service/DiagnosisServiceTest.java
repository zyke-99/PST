package com.zyke.docdiag.service;

import com.zyke.docdiag.model.Diagnosis;
import com.zyke.docdiag.model.Diagnosis;
import com.zyke.docdiag.repository.DiagnosisRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiagnosisServiceTest {

    @Mock
    DiagnosisRepository diagnosisRepository;

    @InjectMocks
    DiagnosisService diagnosisService;

    @Test
    void FindAll_() {
        Diagnosis d1 = new Diagnosis(0, "Diagnosis1", "2015-03-15", 1);
        Diagnosis d2 = new Diagnosis(0, "Diagnosis2", "2015-03-15", 1);
        Diagnosis d3 = new Diagnosis(0, "Diagnosis3", "2015-03-15", 1);
        List<Diagnosis> diagnoses = new LinkedList<>();
        diagnoses.add(d1);
        diagnoses.add(d2);
        diagnoses.add(d3);
        when(diagnosisRepository.findAll()).thenReturn(diagnoses);
        List<Diagnosis> allDiagnoses = diagnosisService.findAll();
        verify(diagnosisRepository).findAll();
        assertEquals(3, allDiagnoses.size());
    }

    @Test
    void FindById_() {
        Diagnosis d = new Diagnosis(0, "Illness", "2015-03-15", 1);
        when(diagnosisRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(d));
        Diagnosis diagnosis = diagnosisService.findById(1);
        verify(diagnosisRepository).findById(Mockito.anyLong());
        assertNotNull(diagnosis);
    }

    @Test
    void DeleteById_() {
        diagnosisService.deleteById(1);
        verify(diagnosisRepository).deleteById(Mockito.anyLong());
    }

    @Test
    void Delete_ProvidedDiagnosisObject_CallsDeleteInDiagnosisAndDiagnosisRepository() {
        Diagnosis d = new Diagnosis(0, "Illness", "2015-03-15", 1);
        diagnosisService.delete(d);
        verify(diagnosisRepository).delete(Mockito.any(Diagnosis.class));
    }

    @Test
    void Add_ProvidedDiagnosisObject_CallsRepositorySaveMethodAndReturnsObject() {
        Diagnosis d = new Diagnosis(0, "Illness", "2015-03-15", 1);
        when(diagnosisRepository.save(Mockito.any(Diagnosis.class))).thenReturn(d);
        Diagnosis savedDiagnosis = diagnosisService.add(d);
        verify(diagnosisRepository).save(Mockito.any(Diagnosis.class));
        assertNotNull(savedDiagnosis);
    }

    @Test
    void Update_ProvidedDiagnosisObject_CallsRepositorySaveMethod() {
        Diagnosis d = new Diagnosis(6,"Illness", "2021-03-15", 1);
        diagnosisService.update(d);
        verify(diagnosisRepository).save(Mockito.any(Diagnosis.class));
    }

}
