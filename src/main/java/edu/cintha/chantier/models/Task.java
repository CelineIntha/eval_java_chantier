package edu.cintha.chantier.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.views.ConsumableView;
import edu.cintha.chantier.views.OperationView;
import edu.cintha.chantier.views.TaskView;
import edu.cintha.chantier.views.UserView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Getter
@Setter
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(TaskView.class)
    protected Integer id;

    @JsonView({TaskView.class, UserView.class})
    @Length(min = 5, max = 30, message = "Le nom de la tâche doit avoir entre 5 et 30 caractères.")
    protected String name;

    @JsonView(TaskView.class)
    protected int duree_en_minutes;

    @JsonView({TaskView.class, ConsumableView.class, UserView.class})
    @ManyToMany
    @JoinTable(name = "task_consumable",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "consumable_id"))
    protected List<Consumable> consumables;

    @OneToMany(mappedBy = "task")
    @JsonView({ConsumableView.class})
    protected List<Operation> operations;
    public int getDureeEnMinutes() {
        return this.duree_en_minutes;
    }


}