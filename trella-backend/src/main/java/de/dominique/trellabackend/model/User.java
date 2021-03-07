package de.dominique.trellabackend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class User extends BaseEntity {

    private String name;
    private String email;
    private String password;
    @OneToMany
    private List<Task> tasks;

}
