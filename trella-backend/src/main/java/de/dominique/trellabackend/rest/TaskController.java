package de.dominique.trellabackend.rest;

import de.dominique.trellabackend.model.dto.TaskDTO;
import de.dominique.trellabackend.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getAllTasksForUser(@RequestParam(required = false) final Long userId) {
        if (userId == null) {
            return taskService.getAllTasks();
        } else {
            return taskService.getAllTasksForUser(userId);
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity postNewTask(@RequestBody TaskDTO taskDTO) {
        boolean success = taskService.createNewTask(taskDTO);

        return success ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }



}
