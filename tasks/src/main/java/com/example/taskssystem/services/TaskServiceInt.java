package com.example.taskssystem.services;

import com.example.taskssystem.entities.Task;
import com.example.taskssystem.exceptions.EntityNotFound;
import java.util.List;
import java.util.UUID;

public interface TaskServiceInt {

    Task create(Task task);

    Task update(Task task,UUID id) throws EntityNotFound;

    Task findById(UUID id) throws EntityNotFound;;

    boolean delete(UUID id) throws EntityNotFound;;

    List<Task> findAll();
}
