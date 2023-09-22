package com.project.assesmentportal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="results")
public class Result {
    /**
     * The unique identifier of the quiz.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resultId;
    
    private int totalMarks;
    
    private int obtainedMarks;
    
    private int attemptedQuestions;
    
    private int totalQuestions;
    
    private String dateTime;
    
    private String userEmail;
    
    private String userName;
    
    private String quizTitle;
    
    private String categoryTitle;
}
