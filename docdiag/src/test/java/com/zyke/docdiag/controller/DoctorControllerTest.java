package com.zyke.docdiag.controller;

import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.service.DoctorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import javax.print.Doc;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(value = DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Test
    public void ShowDoctors_() throws Exception {
        List<Doctor> d = new LinkedList<Doctor>();
        Doctor d1 = new Doctor("firstDoctor", "555");
        Doctor d2 = new Doctor("secondDoctor", "555");
        Doctor d3 = new Doctor("thirdDoctor", "555");
        d.add(d1);
        d.add(d2);
        d.add(d3);
        when(doctorService.findAll()).thenReturn(d);
        RequestBuilder rb = MockMvcRequestBuilders.get("/doctors").accept(MediaType.TEXT_HTML);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("doctors-table"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/doctors-table.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctors"))
                .andReturn();
    }

    @Test
    public void ShowAdd_() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-doctor");
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("doctor"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/doctor.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctor"))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor",  hasProperty("name", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor",  hasProperty("phoneNumber", emptyOrNullString())))
                .andReturn();
    }

    @Test
    void Add_() throws Exception {

        Doctor d = new Doctor("firstDoctor", "555");
        when(doctorService.add(Mockito.any(Doctor.class))).thenReturn(d);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/add-doctor")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Doctor")
                .param("phoneNumber", "555")
                .flashAttr("doctor", new Doctor("Doctor", "555"));
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/doctors"))
                .andReturn();
        verify(doctorService).add(Mockito.any(Doctor.class));
    }

    @Test
    public void ShowUpdate_() throws Exception {
        when(doctorService.findById(Mockito.anyLong())).thenReturn(new Doctor("Doctor", "555"));
        RequestBuilder rb = MockMvcRequestBuilders.get("/update-doctor/1");
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("doctor"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/doctor.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("doctor"))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor",  hasProperty("name", Matchers.equalTo("Doctor"))))
                .andExpect(MockMvcResultMatchers.model().attribute("doctor",  hasProperty("phoneNumber", Matchers.equalTo("555"))))
                .andReturn();
        verify(doctorService).findById(Mockito.anyLong());
    }

    @Test
    void Update_() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/update-doctor/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Doctor")
                .param("phoneNumber", "555")
                .flashAttr("doctor", new Doctor("Doctor", "555"));
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/doctors"))
                .andReturn();
        verify(doctorService).update(Mockito.any(Doctor.class));
    }

    @Test
    void Delete_() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delete-doctor/1");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/doctors"))
                .andReturn();

        verify(doctorService).deleteById(Mockito.anyLong());
    }

}
