package com.zyke.docdiag.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {

    @Test
    void CompareTo_SameDoctor_ShouldReturnZero() {
        Doctor d = new Doctor("Namely", "864445");
        assertEquals(0, d.compareTo(d));
    }

    @Test
    void CompareTo_DoctorAlphabeticallyLesserThanOther_ShouldReturnMinusOne() {
        Doctor d1 = new Doctor("Aaa", "864445");
        Doctor d2 = new Doctor("Bbb", "864445");
        assertEquals(-1, d1.compareTo(d2));
    }

    @Test
    void CompareTo_DoctorAlphabeticallyGreaterThanOther_ShouldReturnOne() {
        Doctor d1 = new Doctor("Bbb", "864445");
        Doctor d2 = new Doctor("Aaa", "864445");
        assertEquals(1, d1.compareTo(d2));
    }

    @Test
    void Equals_SameDoctor_ShouldReturnTrue() {
        Doctor d = new Doctor("Namely", "864445");
        assertTrue(d.equals(d));
    }

    @Test
    void Equals_DifferentDoctors_ShouldReturnFalse() {
        Doctor d1 = new Doctor("First", "554");
        Doctor d2 = new Doctor("Second", "423");
        assertFalse(d1.equals(d2));
    }

    @Test
    void Setters_ModifiedObjectData_CorrectDataSet() {
        String name = "Doctor";
        String phoneNumber = "Phone number";
        Doctor d = new Doctor();
        d.setName(name);
        d.setPhoneNumber(phoneNumber);
        assertAll("Test getters",
                () -> assertEquals(name, d.getName()),
                () -> assertEquals(phoneNumber, d.getPhoneNumber()));
    }

    @Test
    void Getters_CreateDoctorObject_CorrectDataFetched() {
        String name = "Doctor";
        String phoneNumber = "Phone number";
        Doctor d = new Doctor(name, phoneNumber);
        assertAll("Test getters",
                () -> assertEquals(name, d.getName()),
                () -> assertEquals(phoneNumber, d.getPhoneNumber()));
    }

}
