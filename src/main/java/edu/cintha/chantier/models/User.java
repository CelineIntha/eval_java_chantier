package edu.cintha.chantier.models;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.views.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UserView.class)
    protected Integer id;

    @Column(unique = true, length = 50)
    @JsonView({UserView.class, OperationView.class, TaskView.class, ChantierView.class, ConsumableView.class})
    protected String username;

    @Column(length = 100)
    protected String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    protected UserRole role;

    @OneToMany(mappedBy = "ouvrier")
    @JsonView(UserView.class)
    protected List<Operation> operations;


}