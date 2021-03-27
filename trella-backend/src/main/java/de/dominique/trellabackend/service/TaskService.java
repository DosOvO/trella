package de.dominique.trellabackend.service;

import de.dominique.trellabackend.model.Task;
import de.dominique.trellabackend.model.dto.TaskDTO;
import de.dominique.trellabackend.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository,
                       ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    public List<TaskDTO> getAllTasks() {
        return convertToDto(taskRepository.findAll());
    }

    public List<TaskDTO> getAllTasksForUser(final Long userId) {
        return convertToDto(taskRepository.findAllByUserId(userId));
    }

    public boolean changeTask(final TaskDTO taskDTO) {
        if (taskDTO.getId() == null) {
            return false;
        }

        final Optional<Task> present = taskRepository.findById(taskDTO.getId());

        if (present.isEmpty()) {
            return false;
        } else {
            final Task task = present.get();
            task.setDescription(taskDTO.getDescription());
            task.setStatus(taskDTO.getStatus());
            taskRepository.save(task);
        }

        return true;
    }

    public boolean createNewTask(final TaskDTO taskDTO) {
        final Task task = modelMapper.map(taskDTO, Task.class);

        taskRepository.save(task);

        return task.getId() != null;
    }

    private List<TaskDTO> convertToDto(List<Task> tasks) {
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }
}
