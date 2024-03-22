package edu.cintha.chantier.dao;

import edu.cintha.chantier.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}

