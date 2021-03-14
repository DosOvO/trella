package de.dominique.trellabackend.model.dto;

import de.dominique.trellabackend.model.Status;
import lombok.Data;

@Data
public class TaskDTO {

    private Long id;
    private String description;
    private Status status;
    private Long userId;

}
