package com.vanier.grading_api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanier.grading_api.entity.Professor;
import com.vanier.grading_api.entity.Student;
import com.vanier.grading_api.service.ProfessorService;
import com.vanier.grading_api.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private ProfessorService professorService;

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    String username = "admin";
    String password = "admin";

    @Test
    void testDeleteProfessor() throws Exception {
        // Setup
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setFirstName("John");
        professor.setLastName("Doe");
        professor.setEmail("john.doe@example.com");

        // Mock the service findById and delete method
        Mockito.when(professorService.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(professor));
        Mockito.doNothing().when(professorService).delete(Mockito.anyLong());

        // Execute
        mockMvc.perform(
                        delete("/professor/delete?id=1")
                                        .header("Authorization",
                                                        "Basic " + Base64.getEncoder().encodeToString(
                                                                        (username + ":" + password).getBytes()))) // basic
                                                                                                                  // authentication
                        .andDo(print())
                // Assertions
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"));
  

    }

    @Test
    void testDeleteStudentNotModified() throws Exception {
        // Setup
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Jane");
        student.setLastName("Doe");
        student.setEmail("jane.doe@example.com");

        // Mock the service findById and delete methods for not modified scenario
        Mockito.when(studentService.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Mockito.doNothing().when(studentService).delete(Mockito.anyLong());

        // Execute
        mockMvc.perform(
                        delete("/student/delete?id=1")
                                        .header("Authorization",
                                                        "Basic " + Base64.getEncoder().encodeToString(
                                                                        (username + ":" + password).getBytes()))) // basic
                                                                                                                  // authentication
                        .andDo(print())
        // Assertions
                .andExpect(status().isNotModified());
    }

    @Test
    void testFindByIdProfessor() throws Exception {
        // Setup
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setFirstName("John");
        professor.setLastName("Doe");
        professor.setEmail("john.doe@example.com");

        // Mock the service findById method
        Mockito.when(professorService.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(professor));

        // Execute
        mockMvc.perform(
                        get("/professor/findById?id=1")
                                        .header("Authorization",
                                                        "Basic " + Base64.getEncoder().encodeToString(
                                                                        (username + ":" + password).getBytes()))) // basic
                                                                                                                  // authentication
                        .andDo(print())
        // Assert
                .andExpect(status().isAccepted());

    }

    @Test
    void testFindByIdStudentNotFound() throws Exception {
        // Mock the service findById method for not found scenario
        Mockito.when(studentService.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        // Execute
        mockMvc.perform(
                        get("/student/findById?id=1")
                                        .header("Authorization",
                                                        "Basic " + Base64.getEncoder().encodeToString(
                                                                        (username + ":" + password).getBytes()))) // basic
                                                                                                                  // authentication
                        .andDo(print())
        // Assert
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveProfessor() throws JsonProcessingException, Exception {
        // Setup
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setFirstName("John");
        professor.setLastName("Doe");
        professor.setEmail("john.doe@example.com");

        // Mock the service save method
        Mockito.when(professorService.save(Mockito.any(Professor.class)))
                .thenReturn(professor);

        // Execute
        mockMvc.perform(
                post("/professor/save").contentType(MediaType.APPLICATION_JSON)
                                        .content(new ObjectMapper().writeValueAsString(professor))
                                        .header("Authorization",
                                                        "Basic " + Base64.getEncoder().encodeToString(
                                                                        (username + ":" + password).getBytes()))) // basic
                                                                                                                  // authentication
        // Assertions
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"));

    }

    @Test
    void testUpdateProfessor() throws JsonProcessingException, Exception {
        // Setup
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setFirstName("John");
        professor.setLastName("Doe");
        professor.setEmail("john.doe@example.com");

        Professor updatedProfessor = new Professor();
        updatedProfessor.setId(1L);
        updatedProfessor.setFirstName("Updated John");
        updatedProfessor.setLastName("Updated Doe");
        updatedProfessor.setEmail("updated.john.doe@example.com");

        // Mock the needed service methods
        Mockito.when(professorService.findById(1L)).thenReturn(Optional.of(professor));
        Mockito.when(professorService.save(updatedProfessor)).thenReturn(updatedProfessor);

        // Execute
        mockMvc.perform(
                put("/professor/update")
                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(new ObjectMapper().writeValueAsString(updatedProfessor))
                                        .header("Authorization",
                                                        "Basic " + Base64.getEncoder().encodeToString(
                                                                        (username + ":" + password).getBytes()))) // basic
                                                                                                                  // authentication
        // Assert
                .andExpect(MockMvcResultMatchers.status().isAccepted());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Updated John"));
    }

}
