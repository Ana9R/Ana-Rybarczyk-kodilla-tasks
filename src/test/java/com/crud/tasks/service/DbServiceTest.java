package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldGetAllTasks() {
        //given

        //when
        dbService.getAllTasks();

        //then
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindOne() {
        //given
        Long id = 1L;

        //when
        dbService.findOne(id);

        //then
        verify(taskRepository, times(1)).findById(id);
    }

    @Test
    public void shouldSaveTask() {
        //given
        Task task = new Task(1L, "title", "content");

        //when
        dbService.saveTask(task);

        //then
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void shouldGetTask() {
        //given
        Long id = 1L;

        //when
        dbService.getTask(id);

        //then
        verify(taskRepository, times(1)).findById(id);
    }

    @Test
    public void shouldDeleteTask() {
        //given
        Long id = 1L;

        //when
        dbService.deleteTask(id);

        //then
        verify(taskRepository, times(1)).deleteById(id);
    }
}