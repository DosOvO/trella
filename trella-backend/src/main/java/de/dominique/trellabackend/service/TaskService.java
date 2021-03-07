package de.dominique.trellabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskService taskService;

    @Autowired
    public TaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
