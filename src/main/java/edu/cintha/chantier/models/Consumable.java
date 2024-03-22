package edu.cintha.chantier.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.views.ChantierView;
import edu.cintha.chantier.views.ConsumableView;
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
public class Consumable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ConsumableView.class)
    protected Integer id;

    @Length(min = 5, max = 30, message = "Le nom du consommable doit avoir entre 5 et 30 caractères.")
    @JsonView({ConsumableView.class, TaskView.class, UserView.class})
    protected String name;

    @ManyToMany(mappedBy = "consumables")
    protected List<Task> tasks;

}