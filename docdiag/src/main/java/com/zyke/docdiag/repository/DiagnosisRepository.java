package com.zyke.docdiag.repository;

import com.zyke.docdiag.model.Diagnosis;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Long> {
    void deleteAllByDoctorId(long doctorId);
}
