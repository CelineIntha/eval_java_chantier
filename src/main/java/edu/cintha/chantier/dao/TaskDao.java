package edu.cintha.chantier.dao;

import edu.cintha.chantier.models.Consumable;
import edu.cintha.chantier.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskDao extends JpaRepository<Task, Integer> {
    Optional<Task> findByName(String nomRecherche);
}
