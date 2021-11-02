package com.zyke.docdiag.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long patientId;
    private String diagnosis;
    private Date date;
    @ManyToOne
    private Doctor doctor;

    protected Diagnosis() {
    }

    public Diagnosis(long patientId, String diagnosis, Date date, Doctor doctor) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.date = date;
        this.doctor = doctor;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
