package com.project.assesmentportal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.assesmentportal.entities.User;

/**
 * JPA repository interface for managing User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email address.
     * @param email The email address of the user to retrieve.
     * @return An Optional containing the retrieved User entity, if found.
     */
    Optional<User> findByEmail(String email);
}
