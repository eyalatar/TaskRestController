package com.example.taskssystem.exceptions;

import lombok.AllArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
public class EntityNotFound extends RuntimeException {
    private UUID id;
}
