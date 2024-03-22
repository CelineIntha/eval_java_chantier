package edu.cintha.chantier.dao;

import edu.cintha.chantier.models.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChantierDao extends JpaRepository<Chantier, Integer> {
    Optional<Chantier> findByAdresse(String nomRecherche);
}
