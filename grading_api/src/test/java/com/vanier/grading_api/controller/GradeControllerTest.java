package com.vanier.grading_api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanier.grading_api.entity.Grade;
import com.vanier.grading_api.service.GradeService;

@SpringBootTest
@AutoConfigureMockMvc
public class GradeControllerTest {
    
    @MockBean GradeService service;

    @Autowired MockMvc mockMvc;

    @Test
    void testDelete() throws Exception {
        // Setup
        Grade grade = new Grade();
        grade.setId(1L);
        grade.setCategory("midterm");

        // Mock the service findById and delete method
        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(Optional.of(grade));
        Mockito.doNothing().when(service).delete(Mockito.anyLong());

        // Execute
        mockMvc.perform(
                delete("/grade/delete?id=1")).andDo(print())
        // Assertions
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        Grade grade = new Grade();
        grade.setId(1L);
        grade.setCategory("midterm");

        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(Optional.of(grade));

        // Execute
        mockMvc.perform(
                get("/grade/findById?id=1")).andDo(print())
        // Assert
                .andExpect(status().isAccepted());
    }

    @Test
    void testSave() throws Exception {
        // Setup
        Grade grade = new Grade();
        grade.setId(1L);
        grade.setCategory("midterm");

        // Mock the service save method
        Mockito.when(service.save(Mockito.any(Grade.class))).thenReturn(grade);

        // Execute
        mockMvc.perform(
                post("/grade/save").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(grade)))
        // Assertions
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("midterm"));
    }

    @Test
    void testUpdate() throws Exception {
       /* // Setup
        Grade grade = new Grade();
        grade.setId(1L);
        grade.setCategory("midterm");

        Grade updatedGrade = new Grade();
        updatedGrade.setId(1L);
        grade.setCategory("final");

        // Mock the needed service methods
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(grade));
        Mockito.when(service.save(updatedGrade)).thenReturn(updatedGrade);

        // Execute
        mockMvc.perform(
                put("/grade/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedGrade)))
        // Assert
                .andExpect(MockMvcResultMatchers.status().isAccepted());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.category").value("final")); */

                // Setup
    Grade grade = new Grade();
    grade.setId(1L);
    grade.setIdProfessor(123L);
    grade.setIdStudent(456L);
    grade.setIdAssignature(789L);
    grade.setCategory("Test");
    grade.setAssesmentWeight(0.5);
    grade.setGradeValue(80.0);
    grade.setDateSubmission(new Date());

    Grade updatedGrade = new Grade();
    updatedGrade.setId(1L);
    updatedGrade.setIdProfessor(123L);
    updatedGrade.setIdStudent(456L);
    updatedGrade.setIdAssignature(789L);
    updatedGrade.setCategory("Updated Test");
    updatedGrade.setAssesmentWeight(0.6);
    updatedGrade.setGradeValue(85.0);
    updatedGrade.setDateSubmission(new Date());

    // Mock the needed service methods
    Mockito.when(service.findById(1L)).thenReturn(Optional.of(grade));
    Mockito.when(service.save(updatedGrade)).thenReturn(updatedGrade);

    // Execute
    mockMvc.perform(
            put("/grade/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(updatedGrade)))
            // Assert
            .andExpect(MockMvcResultMatchers.status().isAccepted())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
            .andExpect(MockMvcResultMatchers.jsonPath("$.idProfessor").value(123L))
            .andExpect(MockMvcResultMatchers.jsonPath("$.idStudent").value(456L))
            .andExpect(MockMvcResultMatchers.jsonPath("$.idAssignature").value(789L))
            .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Updated Test"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.assesmentWeight").value(0.6))
            .andExpect(MockMvcResultMatchers.jsonPath("$.gradeValue").value(85.0));
            // Add more assertions based on your actual structure if needed

    }
}
