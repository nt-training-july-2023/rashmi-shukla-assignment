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

/**
 * Entity class representing a result.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "results")
public class Result {
    /**
     * The unique identifier of the result.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resultId;

    /**
     * totalMarks of result.
     */
    private int totalMarks;

    /**
     * The obtainedMarks in result.
     */
    private int obtainedMarks;

    /**
     * The number of attempted questions.
     */
    private int attemptedQuestions;

    /**
     * The total number of questions.
     */
    private int totalQuestions;

    /**
     * The date and time.
     */
    private String dateTime;

    /**
     * email of an user.
     */
    private String userEmail;

    /**
     * name of the user.
     */
    private String userName;

    /**
     * The title of the quiz to which result belongs.
     */
    private String quizTitle;

    /**
     * The title of the category to which quiz belongs.
     */
    private String categoryTitle;
}
