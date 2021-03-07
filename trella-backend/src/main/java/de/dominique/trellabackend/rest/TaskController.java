package de.dominique.trellabackend.rest;

import de.dominique.trellabackend.model.Task;
import de.dominique.trellabackend.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public List<Task> getAllTasks(final Long userId) {
        return new ArrayList<>();
    }

}
