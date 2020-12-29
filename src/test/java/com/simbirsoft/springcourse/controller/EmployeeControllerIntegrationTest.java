package com.simbirsoft.springcourse.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.simbirsoft.springcourse.dto.EmployeeDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findById_Success() throws Exception {

        RequestBuilder requestBuilder = get("/api/v1/employee/1")
                .with(httpBasic("admin", "admin"));

        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void findById_Unauthorized() throws Exception {

        RequestBuilder requestBuilder = get("/api/v1/employee/1");

        mvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized());
    }


    @Test
    void addEmployee_Success() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("John");
        employeeDto.setSurname("Doe");
        employeeDto.setDateOfBirth(LocalDate.of(1980, 11, 30));
        employeeDto.setDateOfEmployment(LocalDate.of(2010, 5, 11));

        RequestBuilder requestBuilder = post("/api/v1/employee/create")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeDto));

        mvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void addEmployee_Unauthorized() throws Exception {
        RequestBuilder requestBuilder = post("/api/v1/employee/create");

        mvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized());
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void delete_Success() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Bob");
        employeeDto.setSurname("Doe");
        employeeDto.setDateOfBirth(LocalDate.of(1985, 10, 10));
        employeeDto.setDateOfEmployment(LocalDate.of(2015, 12, 27));

        RequestBuilder createRequestBuilder = post("/api/v1/employee/create")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employeeDto));

        mvc.perform(createRequestBuilder)
                .andExpect(status()
                        .isCreated());

        RequestBuilder requestRequestBuilder = get("/api/v1/employee/2")
                .with(httpBasic("admin", "admin"));

        mvc.perform(requestRequestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();

        RequestBuilder deleteRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/employee/2")
                .with(httpBasic("admin", "admin"));

        mvc.perform(deleteRequestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void delete_Unauthorized() throws Exception {

        RequestBuilder deleteRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/employee/2");

        mvc.perform(deleteRequestBuilder)
                .andExpect(status().isUnauthorized());
    }
}