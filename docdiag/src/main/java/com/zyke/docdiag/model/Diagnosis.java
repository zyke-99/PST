package com.zyke.docdiag.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Diagnosis implements Comparable<Diagnosis>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long patientId;
    private String diagnosisName;
    private String date;
    private long doctorId;

    public Diagnosis() {
    }

    public Diagnosis(long patientId, String diagnosisName, String date, long doctorId) {
        this.patientId = patientId;
        this.diagnosisName = diagnosisName;
        this.date = date;
        this.doctorId = doctorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return patientId == diagnosis.patientId && doctorId == diagnosis.doctorId && diagnosisName.equals(diagnosis.diagnosisName) && date.equals(diagnosis.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, diagnosisName, date, doctorId);
    }

    @Override
    public int compareTo(Diagnosis o) {
        return this.diagnosisName.toLowerCase().compareTo(o.diagnosisName.toLowerCase());
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", diagnosisName='" + diagnosisName + '\'' +
                ", date='" + date + '\'' +
                ", doctorId=" + doctorId +
                '}';
    }
}
