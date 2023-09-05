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
    
    private long quizId;
    
    private String quizTitle;
    
    private String quizDescription;
    
    private int quizTimer;
    
    private CategoryDto category;
}
