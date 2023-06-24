package com.example.taskssystem.config;

import com.example.taskssystem.entities.Task;
import com.example.taskssystem.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {
        return args -> {
            log.info("creating" + repository.save(new Task().toBuilder().name("first task name").priority(1).build()));
            log.info("creating " + repository.save(new Task().toBuilder().name("second task name").priority(3).build()));
            log.info("creating " + repository.save(new Task().toBuilder().name("third task name").priority(5).build()));
            log.info("getting " + repository.findAll());
        };
    }
}
