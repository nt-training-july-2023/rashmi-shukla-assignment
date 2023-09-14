package com.project.assesmentportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.assesmentportal.entities.Question;

/**
 * JPA repository interface for managing Question entities.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
