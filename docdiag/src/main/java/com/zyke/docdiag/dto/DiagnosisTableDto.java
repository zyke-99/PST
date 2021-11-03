package com.zyke.docdiag.dto;

public class DiagnosisTableDto {
    private long id;
    private long patientId;
    private String diagnosisName;
    private String date;
    private String doctorName;
    private long doctorId;

    public DiagnosisTableDto(long id, long patientId, String diagnosisName, String date, String doctorName, long doctorId) {
        this.id = id;
        this.patientId = patientId;
        this.diagnosisName = diagnosisName;
        this.date = date;
        this.doctorName = doctorName;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "DiagnosisTableDto{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", diagnosisName='" + diagnosisName + '\'' +
                ", date='" + date + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorId=" + doctorId +
                '}';
    }
}
