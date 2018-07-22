package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetTasks() throws Exception {
        // given
        Task task = new Task(1L, "Title", "Content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(taskDto);

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        // when & then
        mockMvc.perform(get("/v1/task/getTasks").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                // Trello board fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Title")))
                .andExpect(jsonPath("$[0].content", is("Content")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        // given
        Task task = new Task(1L, "Title", "Content");
        Optional<Task> optionalTask = Optional.of(task);
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");

        when(service.getTask(1L)).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // when & then
        mockMvc.perform(get("/v1/task/getTask").contentType(APPLICATION_JSON).param("taskId", "1"))
                .andExpect(status().isOk())
                // Trello board fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Title")))
                .andExpect(jsonPath("$.content", is("Content")));

    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //given
        String taskId = "1";
        Task task = new Task(1L, "Title", "Content");
        Optional<Task> optionalTask = Optional.of(task);
        when(service.getTask(1L)).thenReturn(optionalTask);

        // when & then
        mockMvc.perform(delete("/v1/task/deleteTask").contentType(APPLICATION_JSON).param("taskId", taskId))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteTask(1L);
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        // given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task task = new Task(1L, "Title", "Content");

        TaskDto updatedTaskDto = new TaskDto(1L, "Title33", "Content");
        Task updatedTask = new Task(1l, "Title33", "Content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(updatedTask);
        when(taskMapper.mapToTaskDto(updatedTask)).thenReturn(updatedTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // when & then
        mockMvc.perform(put("/v1/task/updateTask").contentType(APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Title33")))
                .andExpect(jsonPath("$.content", is("Content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        // given
        TaskDto taskDto = new TaskDto(null, "Title", "Content");
        Task task = new Task(null, "Title", "Content");

        TaskDto createdTaskDto = new TaskDto(1L, "Title", "Content");
        Task createdTask = new Task(1l, "Title", "Content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(createdTask);
        when(taskMapper.mapToTaskDto(createdTask)).thenReturn(createdTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // when & then
        mockMvc.perform(post("/v1/task/createTask").contentType(APPLICATION_JSON).content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Title")))
                .andExpect(jsonPath("$.content", is("Content")));
    }
}