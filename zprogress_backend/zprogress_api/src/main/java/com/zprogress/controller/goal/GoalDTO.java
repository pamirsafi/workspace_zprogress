package com.zprogress.controller.goal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zprogress.domain.Goal;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Relation(collectionRelation = "goals")
public class GoalDTO implements Serializable {

    @JsonIgnore
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    private LocalDate deadline;

    public GoalDTO(Goal goal) {
        this.setName(goal.getName());
        this.setDeadline(goal.getDeadline());
        this.setDescription(goal.getDescription());
        this.setId(goal.getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
