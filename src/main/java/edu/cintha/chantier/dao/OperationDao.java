package edu.cintha.chantier.dao;

import edu.cintha.chantier.models.Consumable;
import edu.cintha.chantier.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationDao extends JpaRepository<Operation, Integer> {
    Optional<Operation> findByDescription(String nomRecherche);
}
