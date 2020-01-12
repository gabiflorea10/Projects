package com.ds.Assignment1.medicalApplication.repositories;

import com.ds.Assignment1.medicalApplication.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID user_id);
    Integer deleteByUsername(String username);
    Optional<User> findByUsername(String username);
    User findUserByUsername(String username);
    Optional<User> findByRole(String role);
    List<User> findAllByRole(String role);
    Optional<User> findByUsernameAndPassword(String username, String password);
    User findUserByName(String name);
}
