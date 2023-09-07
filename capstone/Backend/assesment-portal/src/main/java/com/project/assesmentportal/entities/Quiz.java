package com.project.assesmentportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a quiz.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "quizes")
public class Quiz {
    /**
     * The unique identifier of the quiz.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;

    /**
     * The title of the quiz.
     */
    @Column(name = "quiz_title", nullable = false, unique = true)
    private String quizTitle;

    /**
     * The description of the quiz.
     */
    @Column(name = "quiz_desc")
    private String quizDescription;

    /**
     * The timer for quiz.
     */
    private int quizTimer;

    /**
     * many to one relation between category and quiz.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    /**
     * getter for category.
     * @return category the quiz belongs to.
     */
    public Category getCategory() {
        return new Category(category.getCategoryId(),
                category.getCategoryTitle(),
                category.getCategoryDescription());
    }

    /**
     * setter for the quiz.
     * @param category which the quiz belongs to.
     */
    public void setCategory(final Category category) {
        this.category = new Category(category.getCategoryId(),
                category.getCategoryTitle(),
                category.getCategoryDescription());
    }

    /**
     * all args constructor for quiz.
     * @param quizId unique id for quiz.
     * @param quizTitle title of the quiz.
     * @param quizDesc description of the quiz.
     * @param time timer for the quiz.
     */
    public Quiz(final long quizId, final String quizTitle,
            final String quizDesc, final int time) {
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.quizDescription = quizDesc;
        this.quizTimer = time;
    }
}
