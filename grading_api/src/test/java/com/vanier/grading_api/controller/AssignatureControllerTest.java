package com.vanier.grading_api.controller;

//static imports required for the payload
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanier.grading_api.entity.Assignature;
import com.vanier.grading_api.service.AssignatureService;

@SpringBootTest
@AutoConfigureMockMvc
public class AssignatureControllerTest {

    //this injects the service with the implementation class
    @MockBean AssignatureService service;

    @Autowired MockMvc mockMvc;

    // Simulate basic authentication by adding username and password to the request
    // headers
    String username = "admin";
    String password = "admin";

    @Test
    void testDelete() throws JsonProcessingException, Exception {
        //Setup
        Assignature assignature = new Assignature();
        assignature.setId(1L);
        assignature.setName("software dev");
        assignature.setCode("abc123");      
        assignature.setCredits("4");
        assignature.setStatus("active");
        
        // Mock the service findById and delete method since they work within
        //the delete controller method
        Mockito.when(service.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(assignature));
        Mockito.doNothing().when(service).delete(Mockito.anyLong());


        //Execute
        mockMvc.perform(
                delete("/assignature/delete?id=1")
                        .header("Authorization",
                                "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()))) // basic
                                                                                                                        // authentication
                .andDo(print())
        //Assertions
            .andExpect(status().isAccepted())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("software dev")); 
    }

    @Test
    void testFindById() throws Exception {
        //set up
        Assignature assignature = new Assignature();
        assignature.setId(1L);
        assignature.setCode("abc123");      
        assignature.setName("software dev");
        assignature.setStatus("active");

        Mockito.when(service.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(assignature));

        //Execute
        mockMvc.perform(
                get("/assignature/findById?id=1")
                        .header("Authorization",
                                "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()))) // basic
                                                                                                                        // authentication
                .andDo(print())
        //Assert
        .andExpect(status().isFound());
    }

    @Test
    void testSave() throws Exception {
        //Setup
        Assignature assignature = new Assignature();
        assignature.setId(1L);
        assignature.setName("software dev");
        assignature.setCode("abc123");      
        assignature.setCredits("4");
        assignature.setStatus("active");
        
        // Mock the service save method
        Mockito.when(service.save(Mockito.any(Assignature.class)))
        .thenReturn(assignature);

        //Execute
        mockMvc.perform(
            post("/assignature/save").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(assignature))
                        .header("Authorization",
                                "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()))) // basic
                                                                                                                        // authentication)
        //Assertions
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("software dev"));
    }

    @Test
    void testUpdate() throws Exception {
       // Setup
        Assignature assignature = new Assignature();
        assignature.setId(1L);
        assignature.setName("software dev");
        assignature.setCode("abc123");
        assignature.setCredits("4");
        assignature.setStatus("active");

        Assignature updatedAssignature = new Assignature();
        updatedAssignature.setId(1L);
        updatedAssignature.setName("software dev");
        updatedAssignature.setCode("abc456");
        updatedAssignature.setCredits("2");
        updatedAssignature.setStatus("inactive");

        // Mock the needed service methods
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(updatedAssignature));
        Mockito.when(service.save(updatedAssignature)).thenReturn(updatedAssignature);

        // Execute
        mockMvc.perform(
                put("/assignature/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedAssignature))
                        .header("Authorization",
                                "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()))) // basic
                                                                                                                        // authentication)
                .andDo(print())
        //Assert
                .andExpect(MockMvcResultMatchers.status().isAccepted());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("software dev"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.credits").value("2"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("inactive"));
    
    }
}
