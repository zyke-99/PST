package com.zyke.docdiag.controller;

import com.zyke.docdiag.dto.DiagnosisTableDto;
import com.zyke.docdiag.model.Diagnosis;
import com.zyke.docdiag.service.DiagnosisService;
import com.zyke.docdiag.service.DiagnosisService;
import com.zyke.docdiag.service.DoctorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = DiagnosisController.class)
public class DiagnosisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiagnosisService diagnosisService;

    @MockBean
    private DoctorService doctorService;

    @Test
    public void ShowDiagnoses_() throws Exception {
        List<DiagnosisTableDto> d = new LinkedList<DiagnosisTableDto>();
        DiagnosisTableDto d1 = new DiagnosisTableDto(1,0, "Diagnosis1", "2015-03-15", "John", 1);
        DiagnosisTableDto d2 = new DiagnosisTableDto(2,0, "Diagnosis2", "2015-03-15", "John", 1);
        DiagnosisTableDto d3 = new DiagnosisTableDto(3,0, "Diagnosis3", "2015-03-15", "John", 1);;
        d.add(d1);
        d.add(d2);
        d.add(d3);
        when(diagnosisService.findAllTableDto()).thenReturn(d);
        RequestBuilder rb = MockMvcRequestBuilders.get("/diagnoses").accept(MediaType.TEXT_HTML);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("diagnoses-table"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/diagnoses-table.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("diagnoses"))
                .andReturn();
    }

    @Test
    public void ShowAdd_() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-diagnosis");
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("diagnosis"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/diagnosis.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("diagnosis"))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnosis",  hasProperty("diagnosisName")))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnosis",  hasProperty("patientId")))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnosis",  hasProperty("doctorId")))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnosis",  hasProperty("date")))
                .andReturn();
    }

    @Test
    void Add_() throws Exception {

        Diagnosis d = new Diagnosis(0, "Diagnosis1", "2015-03-15", 1);
        when(diagnosisService.add(Mockito.any(Diagnosis.class))).thenReturn(d);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/add-diagnosis")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("patientId", "0")
                .param("diagnosisName", "Diagnosis1")
                .param("date", "2015-03-15")
                .param("doctorId", "1")
                .flashAttr("diagnosis", new Diagnosis(0, "Diagnosis1", "2015-03-15", 1));
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/diagnoses"))
                .andReturn();
        verify(diagnosisService).add(Mockito.any(Diagnosis.class));
    }

    @Test
    public void ShowUpdate_() throws Exception {
        when(diagnosisService.findById(Mockito.anyLong())).thenReturn(new Diagnosis(0, "Diagnosis1", "2015-03-15", 1));
        RequestBuilder rb = MockMvcRequestBuilders.get("/update-diagnosis/1");
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("diagnosis"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/diagnosis.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("diagnosis"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("diagnosis"))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnosis",  hasProperty("diagnosisName", Matchers.equalTo("Diagnosis1"))))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnosis",  hasProperty("patientId", Matchers.equalTo(0L))))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnosis",  hasProperty("doctorId", Matchers.equalTo(1L))))
                .andExpect(MockMvcResultMatchers.model().attribute("diagnosis",  hasProperty("date", Matchers.equalTo("2015-03-15"))))
                .andReturn();
        verify(diagnosisService).findById(Mockito.anyLong());
    }

    @Test
    void Update_() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/update-diagnosis/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("patientId", "0")
                .param("diagnosisName", "Diagnosis1")
                .param("date", "2015-03-15")
                .param("doctorId", "1")
                .flashAttr("diagnosis", new Diagnosis(0, "Diagnosis1", "2015-03-15", 1));
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/diagnoses"))
                .andReturn();
        verify(diagnosisService).update(Mockito.any(Diagnosis.class));
    }

    @Test
    void Delete_() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delete-diagnosis/1");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/diagnoses"))
                .andReturn();

        verify(diagnosisService).deleteById(Mockito.anyLong());
    }


}
