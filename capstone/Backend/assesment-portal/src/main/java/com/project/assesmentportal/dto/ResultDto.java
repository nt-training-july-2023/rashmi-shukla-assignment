package com.project.assesmentportal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ResultDto for Result entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    /**
     * The unique identifier of the result.
     */
    private long resultId;

    /**
     * totalMarks of result.
     */
    @Min(value = 0, message = "Enter valid total marks")
    private int totalMarks;

    /**
     * The obtainedMarks in result.
     */
    @Min(value = 0, message = "Enter valid obtained marks")
    private int obtainedMarks;

    /**
     * The number of attempted questions.
     */
    @Min(value = 0, message = "Enter valid Attempted Questions.")
    private int attemptedQuestions;

    /**
     * The total number of questions.
     */
    private int totalQuestions;

    /**
     * The date and time.
     */
    @NotBlank(message = "Date and time is required.")
    private String dateTime;

    /**
     * email of an user.
     */
    @NotBlank(message = "Email is required.")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@nucleusteq\\.com$",
            message = "Email domain must be @nucleusteq.com")
    private String userEmail;

    /**
     * name of the user.
     */
    @NotBlank(message = "user name is required")
    private String userName;

    /**
     * The title of the quiz to which result belongs.
     */
    @NotBlank(message = "Quiz title is required.")
    private String quizTitle;

    /**
     * The title of the category to which quiz belongs.
     */
    @NotBlank(message = "Category title is required.")
    private String categoryTitle;
}
