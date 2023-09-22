package com.project.assesmentportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
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
