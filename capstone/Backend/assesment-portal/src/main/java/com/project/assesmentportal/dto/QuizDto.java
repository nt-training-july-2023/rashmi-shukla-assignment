package com.project.assesmentportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * quizDto for quiz entity.
 */
@Getter
@Setter
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

    /**
     * get category of quiz.
     * @return category.
     */
    public final CategoryDto getCategory() {
        return new CategoryDto(category.getCategoryId(),
                category.getCategoryTitle(),
                category.getCategoryDescription());
    }

    /**
     * set quiz category.
     * @param cat to be set.
     */
    public final void setCategory(final CategoryDto cat) {
        this.category = new CategoryDto(cat.getCategoryId(),
                cat.getCategoryTitle(),
                cat.getCategoryDescription());
    }

    /**
     * all args constructor.
     * @param qId          id of the quiz.
     * @param qTitle       title of the quiz.
     * @param qDescription description of the quiz.
     * @param qTimer       timer for the quiz.
     * @param cat        of the quiz.
     */
    public QuizDto(final long qId, final String qTitle,
            final String qDescription, final int qTimer,
            final CategoryDto cat) {
        this.quizId = qId;
        this.quizTitle = qTitle;
        this.quizDescription = qDescription;
        this.quizTimer = qTimer;
        this.category = new CategoryDto(cat.getCategoryId(),
                cat.getCategoryTitle(),
                cat.getCategoryDescription());
    }
}
