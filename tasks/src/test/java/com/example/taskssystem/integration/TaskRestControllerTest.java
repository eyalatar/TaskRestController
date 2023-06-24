package com.example.taskssystem.integration;

import com.example.taskssystem.controller.TaskController;
import com.example.taskssystem.entities.Task;
import com.example.taskssystem.services.TaskServiceInt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TaskController.class)
public class TaskRestControllerTest {

    public static final String CONTENT_TYPE = "application/json";
    public static final String BASE_URL = "/tasks";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskServiceInt taskService;

    @Test
    void whenGetOnAll_thenReturns200() throws Exception {
        mockMvc.perform(get(BASE_URL+"/getall")
                        .contentType(CONTENT_TYPE))
                .andExpect(status().isOk());
    }
    @Test
    void whenPostOnCreate_thenReturns200() throws Exception {
        Task t = new Task(UUID.randomUUID(),"t1",2);
        mockMvc.perform(post(BASE_URL+"/create")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(t)))
                .andExpect(status().isOk());
    }

    @Test
    void whenPostInvalidOnCreateTask_thenReturns400() throws Exception {
        Object t = new Object();
        mockMvc.perform(post(BASE_URL+"/create")
                        .contentType(CONTENT_TYPE)
                        .content("sss"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void whenDeletetInvalidDeleteTask_thenReturns400() throws Exception {
        mockMvc.perform(delete(BASE_URL+"/delete/notuuid")
                        .contentType(CONTENT_TYPE))
                .andExpect(status().isBadRequest());
    }


    @Test
    void whenValidInput_thenMapsToBusinessModel() throws Exception {
        final Task t = new Task(null,"t1",2);
        mockMvc.perform(post(BASE_URL+"/create")
                        .contentType(CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(t)))
                .andExpect(status().isOk());

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskService, times(1)).create(taskCaptor.capture());
        assertTrue(taskCaptor.getValue().getName().equals(t.getName()));
        assertTrue(taskCaptor.getValue().getPriority()==t.getPriority());
    }


}
