package com.example.taskssystem.services;

import com.example.taskssystem.entities.Task;
import com.example.taskssystem.exceptions.EntityNotFound;
import com.example.taskssystem.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskServiceInt {

    private final TaskRepository taskRepository;

    @Override
    public Task create(Task task) {
        // todo validation
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task, UUID id) throws EntityNotFound {
        Optional<Task> t = taskRepository.findById(id);
        return t.orElseThrow(() -> new EntityNotFound(id));

    }

    @Override
    public Task findById(UUID id) throws EntityNotFound {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new EntityNotFound(id));
    }

    @Override
    public boolean delete(UUID id) throws EntityNotFound {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFound(id);
        }
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
