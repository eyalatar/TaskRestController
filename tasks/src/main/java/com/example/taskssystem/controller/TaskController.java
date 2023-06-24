package com.example.taskssystem.controller;

import com.example.taskssystem.entities.Task;
import com.example.taskssystem.exceptions.EntityNotFound;
import com.example.taskssystem.repositories.TaskRepository;
import com.example.taskssystem.services.TaskServiceImpl;
import com.example.taskssystem.services.TaskServiceInt;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskServiceInt taskService;

    @PostMapping("/tasks/create")
    public Task newTask(@RequestBody @Valid @NotNull Task newTask) {
        return taskService.create(newTask);
    }

    @GetMapping("/tasks/get/{id}")
    public Task getTask(@PathVariable @Valid @NotNull UUID id) throws EntityNotFound {
        return taskService.findById(id);
    }

    @DeleteMapping("/tasks/delete/{id}")
    public void deleteTask(@PathVariable @Valid @NotNull UUID id) throws EntityNotFound {
        taskService.delete(id);
    }

    @GetMapping("/tasks/getall")
    List<Task> all() {
        return taskService.findAll();
    }

    @PutMapping("/tasks/update/{id}")
    Task updateTask(@RequestBody Task newTask, @PathVariable UUID id) throws EntityNotFound {
        return taskService.update(newTask, id);
    }
}
