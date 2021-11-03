package com.zyke.docdiag.controller;

import com.zyke.docdiag.model.Doctor;
import com.zyke.docdiag.service.DoctorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(value = DoctorRestController.class)
public class DoctorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DoctorService doctorService;

    @Test
    void GetAllDoctors_() throws Exception {

        List<Doctor> d = new LinkedList<Doctor>();
        Doctor d1 = new Doctor("firstDoctor", "555");
        d1.setId(1);
        Doctor d2 = new Doctor("secondDoctor", "555");
        d2.setId(2);
        Doctor d3 = new Doctor("thirdDoctor", "555");
        d3.setId(3);
        d.add(d1);
        d.add(d2);
        d.add(d3);
        when(doctorService.findAll()).thenReturn(d);


        RequestBuilder rb = MockMvcRequestBuilders
                .get("/api/doctor")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expected = "["
                + "{\"id\":1,\"name\":\"firstDoctor\",\"phoneNumber\":\"555\"},"
                + "{\"id\":2,\"name\":\"secondDoctor\",\"phoneNumber\":\"555\"},"
                + "{\"id\":3,\"name\":\"thirdDoctor\",\"phoneNumber\":\"555\"}"
                + "]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }


    @Test
    void GetDoctorById_() throws Exception {
        Doctor d1 = new Doctor("firstDoctor", "555");
        d1.setId(1);
        when(doctorService.findById(Mockito.anyLong())).thenReturn(d1);


        RequestBuilder rb = MockMvcRequestBuilders
                .get("/api/doctor/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expected = "{\"id\":1,\"name\":\"firstDoctor\",\"phoneNumber\":\"555\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void AddDoctor_() throws Exception {
        Doctor d1 = new Doctor("firstDoctor", "555");
        d1.setId(1);

        when(doctorService.add(Mockito.any(Doctor.class))).thenReturn(d1);

        String doctorJSON = "{\"id\":1,\"name\":\"firstDoctor\",\"phoneNumber\":\"555\"}";

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/api/doctor")
                .content(doctorJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/doctor/1"))
                .andReturn();

        verify(doctorService).add(Mockito.any(Doctor.class));
    }

    @Test
    void DeleteDoctorById_() throws Exception {

        RequestBuilder rb = MockMvcRequestBuilders
                .delete("/api/doctor/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(doctorService).deleteById(Mockito.anyLong());

    }

}
