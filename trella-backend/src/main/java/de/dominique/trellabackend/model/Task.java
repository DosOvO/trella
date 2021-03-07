package de.dominique.trellabackend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Task extends BaseEntity {

    private String name;
    private Status status;
    @ManyToOne
    private User user;

}
