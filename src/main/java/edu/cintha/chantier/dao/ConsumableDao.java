package edu.cintha.chantier.dao;

import edu.cintha.chantier.models.Consumable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumableDao extends JpaRepository<Consumable, Integer> {
    Optional<Consumable> findByName(String nomRecherche);
}
