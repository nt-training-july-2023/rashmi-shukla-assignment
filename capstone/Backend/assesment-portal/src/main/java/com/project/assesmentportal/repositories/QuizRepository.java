package com.project.assesmentportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.assesmentportal.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
