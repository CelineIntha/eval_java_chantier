package edu.cintha.chantier.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.views.ChantierView;
import edu.cintha.chantier.views.OperationView;
import edu.cintha.chantier.views.TaskView;
import edu.cintha.chantier.views.UserView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Getter
@Setter
public class Chantier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ChantierView.class)
    protected Integer id;

    @Length(min = 5, max = 60, message = "L'adresse doit avoir entre 5 et 60 caractères.")
    @NotNull(message = "L'adresse est obligatoire.")
    @JsonView({ChantierView.class, OperationView.class, UserView.class})
    protected String adresse;

    @ManyToOne
    @JsonView({OperationView.class})
    @JoinColumn(name = "ouvrier_id")
    protected User ouvrier;

    @ManyToOne
    @JsonView({OperationView.class})
    @JoinColumn(name = "client_id")
    protected User customer;


    @JsonView({OperationView.class})
    @OneToMany(mappedBy = "chantier", cascade = CascadeType.ALL)
    protected List<Operation> operations;

}