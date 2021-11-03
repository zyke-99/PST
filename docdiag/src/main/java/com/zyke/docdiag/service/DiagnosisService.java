package com.zyke.docdiag.service;

import com.zyke.docdiag.dto.DiagnosisTableDto;
import com.zyke.docdiag.model.Diagnosis;
import com.zyke.docdiag.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DiagnosisService {

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Autowired
    DoctorService doctorService;

    public List<Diagnosis> findAll() {
        return (List<Diagnosis>) diagnosisRepository.findAll();
    }

    public Diagnosis findById(long id) {
        return diagnosisRepository.findById(id).get();
    }

    public List<DiagnosisTableDto> findAllTableDto() {
        List<DiagnosisTableDto> dtos = new LinkedList<DiagnosisTableDto>();
        List<Diagnosis> diagnosisList = (List<Diagnosis>) diagnosisRepository.findAll();
        for (Diagnosis diagnosis: diagnosisList) {
            dtos.add(new DiagnosisTableDto(
                    diagnosis.getId(),
                    diagnosis.getPatientId(),
                    diagnosis.getDiagnosisName(),
                    diagnosis.getDate(),
                    doctorService.findById(diagnosis.getDoctorId()).getName(),
                    diagnosis.getDoctorId()
            ));
        }
        return dtos;
    }

    public void deleteById(long id) {
        diagnosisRepository.deleteById(id);
    }

    public void delete(Diagnosis d) {
        diagnosisRepository.delete(d);
    }

    public Diagnosis add(Diagnosis d) {
        return diagnosisRepository.save(d);
    }

    public void update(Diagnosis d) {
        diagnosisRepository.save(d);
    }

}
