package com.simbirsoft.springcourse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simbirsoft.springcourse.dto.StaffDto;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StaffControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findById_Success() throws Exception {

        RequestBuilder requestBuilder = get("/api/v1/staff/1")
                .with(httpBasic("admin", "admin"));

        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    void findById_Unauthorized() throws Exception {

        RequestBuilder requestBuilder = get("/api/v1/staff/1");

        mvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized());
    }

    @Test
    void addStaff_Success() throws Exception {
        StaffDto staffDto = new StaffDto();
        staffDto.setEmployeeId(3L);
        staffDto.setPosition("manager");
        staffDto.setSalary(5000F);

        RequestBuilder requestBuilder = post("/api/v1/staff/create")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(staffDto));

        mvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void addStaff_Unauthorized() throws Exception {
        RequestBuilder requestBuilder = post("/api/v1/staff/create");

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
        StaffDto staffDto = new StaffDto();
        staffDto.setEmployeeId(4L);
        staffDto.setPosition("manager");
        staffDto.setSalary(7000F);

        RequestBuilder createRequestBuilder = post("/api/v1/staff/create")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(staffDto));

        mvc.perform(createRequestBuilder)
                .andExpect(status().isCreated());

        RequestBuilder requestRequestBuilder = get("/api/v1/staff/2")
                .with(httpBasic("admin", "admin"));

        mvc.perform(requestRequestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();

        RequestBuilder deleteRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/staff/2")
                .with(httpBasic("admin", "admin"));

        mvc.perform(deleteRequestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void delete_Unauthorized() throws Exception {

        RequestBuilder deleteRequestBuilder = MockMvcRequestBuilders.delete("/api/v1/staff/2");

        mvc.perform(deleteRequestBuilder)
                .andExpect(status().isUnauthorized());
    }
}