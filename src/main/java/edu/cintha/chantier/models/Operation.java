package edu.cintha.chantier.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.views.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @JsonView({UserView.class})
    @Length(min = 5, max = 30, message = "La description de l'opération doit avoir entre 5 et 30 caractères.")
    protected String description;

    @Temporal(TemporalType.DATE)
    @JsonView({OperationView.class, TaskView.class})
    protected Date dateDebut;

    @Temporal(TemporalType.DATE)
    @JsonView({OperationView.class, TaskView.class})
    protected Date dateFin;

    @ManyToOne
    @JsonView({OperationView.class, TaskView.class, UserView.class})
    @JoinColumn(name = "task_id")
    protected Task task;

    @ManyToOne
    @JsonView({UserView.class})
    @JoinColumn(name = "chantier_id")
    protected Chantier chantier;
    @ManyToOne

    @JoinColumn(name = "ouvrier_id")
    protected User ouvrier;

}