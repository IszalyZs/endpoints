package com.company.endpoints.controller;

import com.company.endpoints.service.RequestCounterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RequestCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RequestCounterService requestCounterService;

    @Test
    public void getRequests_shouldReturnDefaultMessage() throws Exception {
        this.mockMvc
                .perform(get("/api/requests"))
                .andExpect(status().isOk())
                .andExpect(content().string("It was success"));

    }

    @Test
    public void getStats_shouldReturnStatisticsList() throws Exception {
        this.mockMvc
                .perform(get("/api/stats"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));


    }

}