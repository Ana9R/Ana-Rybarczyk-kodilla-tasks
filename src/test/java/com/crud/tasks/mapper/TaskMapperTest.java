package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskMapperTest {

    private TaskMapper mapper = new TaskMapper();

    @Test
    public void shouldMapToTask() {
        //given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        //when
        Task task = mapper.mapToTask(taskDto);

        //then
        assertEquals(1L, task.getId(), 0);
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }

    @Test
    public void shouldMapToTaskDto() {
        //given
        Task task = new Task(1L, "title", "content");

        //when
        TaskDto taskDto = mapper.mapToTaskDto(task);

        //then
        assertEquals(1L, taskDto.getId(), 0);
        assertEquals("title", taskDto.getTitle());
        assertEquals("content", taskDto.getContent());
    }

    @Test
    public void shoudMapToTaskDtoList() {
        //given
        Task task = new Task(1L, "title", "content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        //when
        List<TaskDto> taskDtos = mapper.mapToTaskDtoList(tasks);

        //then
        assertEquals(1, taskDtos.size());
        assertEquals(1L, taskDtos.get(0).getId(), 0);
        assertEquals("title", taskDtos.get(0).getTitle());
        assertEquals("content", taskDtos.get(0).getContent());
    }
}