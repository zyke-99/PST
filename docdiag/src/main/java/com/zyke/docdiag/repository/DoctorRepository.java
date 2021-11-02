package com.zyke.docdiag.repository;

import com.zyke.docdiag.model.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
