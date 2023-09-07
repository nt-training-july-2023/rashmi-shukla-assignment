package com.project.assesmentportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    /**
     * The unique identifier of the quiz.
     */
    private long quizId;
    
    /**
     * The title of the quiz.
     */
    private String quizTitle;
    
    /**
     * The description of the quiz.
     */
    private String quizDescription;
    
    /**
     * timer for the quiz.
     */
    private int quizTimer;
    
    /**
     * The category of the quiz.
     */
    private CategoryDto category;
}
