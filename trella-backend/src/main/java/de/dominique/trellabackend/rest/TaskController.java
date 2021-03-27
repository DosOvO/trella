package de.dominique.trellabackend.rest;

import de.dominique.trellabackend.model.dto.TaskDTO;
import de.dominique.trellabackend.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/tasks")
    public List<TaskDTO> getAllTasksForUser(@RequestParam(required = false) final Long userId) {
        if (userId == null) {
            return taskService.getAllTasks();
        } else {
            return taskService.getAllTasksForUser(userId);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/tasks")
    public ResponseEntity putTask(@RequestBody TaskDTO taskDTO) {
        LOG.info(taskDTO.toString());
        boolean success = taskService.changeTask(taskDTO);

        return success ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/tasks")
    public ResponseEntity postNewTask(@RequestBody TaskDTO taskDTO) {
        LOG.info(taskDTO.toString());
        boolean success = taskService.createNewTask(taskDTO);

        return success ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }



}
