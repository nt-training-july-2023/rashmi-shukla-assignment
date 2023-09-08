package com.project.assesmentportal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.assesmentportal.entities.Quiz;

/**
 * JPA repository interface for managing Quiz entities.
 */
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    /**
     * retrieves quiz by its title.
     * @param quizTitle title of the quiz to be found.
     * @return existing quiz with matching title.
     */
    Optional<Quiz> findByQuizTitle(String quizTitle);
}
