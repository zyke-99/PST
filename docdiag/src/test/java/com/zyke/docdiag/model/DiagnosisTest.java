package com.zyke.docdiag.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiagnosisTest {

    @Test
    void CompareTo_SameDiagnosis_ShouldReturnTrue() {
        Diagnosis d = new Diagnosis(0, "Illness", "2015-06-03", 1);
        assertEquals(0, d.compareTo(d));
    }

    @Test
    void CompareTo_DiagnosisAlphabeticallyLesserThanOther_ShouldReturnMinusOne() {
        Diagnosis d1 = new Diagnosis(0, "Aaa", "2015-06-03", 1);
        Diagnosis d2 = new Diagnosis(0, "Bbb", "2015-06-03", 1);
        assertEquals(-1, d1.compareTo(d2));
    }

    @Test
    void CompareTo_DiagnosisAlphabeticallyGreaterThanOther_ShouldReturnOne() {
        Diagnosis d1 = new Diagnosis(0, "Bbb", "2015-06-03", 1);
        Diagnosis d2 = new Diagnosis(0, "Aaa", "2015-06-03", 1);
        assertEquals(1, d1.compareTo(d2));
    }

    @Test
    void Equals_SameDiagnosis_ShouldReturnTrue() {
        Diagnosis d = new Diagnosis(0, "Illness", "2015-06-03", 1);
        assertTrue(d.equals(d));
    }

    @Test
    void Equals_DifferentDiagnoses_ShouldReturnFalse() {
        Diagnosis d1 = new Diagnosis(0, "Illness", "2021-05-13", 1);
        Diagnosis d2 = new Diagnosis(1, "Illness", "2021-05-15", 2);
        assertFalse(d1.equals(d2));
    }

    @Test
    void Setters_ModifiedObjectData_CorrectDataSet() {
        String diagnosisName = "Illness";
        String date = "2015-03-15";
        long patientId = 0;
        long doctorId = 0;
        Diagnosis d = new Diagnosis();
        d.setDiagnosisName(diagnosisName);
        d.setDate(date);
        d.setDoctorId(doctorId);
        d.setPatientId(patientId);
        assertAll("Test getters",
                () -> assertEquals(diagnosisName, d.getDiagnosisName()),
                () -> assertEquals(date, d.getDate()),
                () -> assertEquals(patientId, d.getPatientId()),
                () -> assertEquals(doctorId, d.getDoctorId()));
    }

    @Test
    void Getters_CreatedDiagnosisObject_CorrectDataFetched() {
        String diagnosisName = "Illness";
        String date = "2015-03-15";
        long patientId = 0;
        long doctorId = 0;
        Diagnosis d = new Diagnosis(patientId, diagnosisName, date, doctorId);
        assertAll("Test getters",
                () -> assertEquals(diagnosisName, d.getDiagnosisName()),
                () -> assertEquals(date, d.getDate()),
                () -> assertEquals(patientId, d.getPatientId()),
                () -> assertEquals(doctorId, d.getDoctorId()));
    }

}
