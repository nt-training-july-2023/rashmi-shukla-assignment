package com.project.assesmentportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a question.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {
    /**
     * The unique identifier of the question.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    /**
     * The title of the question.
     */
    @Column(name = "question_title", nullable = false)
    private String questionTitle;

    /**
     * The first option as answer.
     */
    private String optionOne;

    /**
     * The second option as answer.
     */
    private String optionTwo;

    /**
     * The third option as answer.
     */
    private String optionThree;

    /**
     * The fourth option as answer.
     */
    private String optionFour;

    /**
     * The correct answer.
     */
    private String answer;

    /**
     * many to one relation between quiz and question.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Quiz quiz;

    /**
     * getter for quiz.
     * @return quiz belongs to.
     */
    public final Quiz getQuiz() {
        if (quiz != null) {
            return new Quiz(quiz.getQuizId(), quiz.getQuizTitle(),
                    quiz.getQuizDescription(), quiz.getQuizTimer(),
                    quiz.getCategory());
        }
        return null;
    }

    /**
     * setter for the quiz.
     * @param qz which the quiz belongs to.
     */
    public final void setQuiz(final Quiz qz) {
        if (qz != null) {
            this.quiz = new Quiz(qz.getQuizId(), qz.getQuizTitle(),
                    qz.getQuizDescription(), qz.getQuizTimer(),
                    qz.getCategory());
        } else {
            this.quiz = null;
        }
    }

    /**
     * all args constructor for question.
     * @param qId    id for question
     * @param qTitle title for question
     * @param optOne   option 1
     * @param optTwo   option 2
     * @param optThree   option 3
     * @param optFour   option 4
     * @param ans    correct answer
     */
    public Question(final long qId, final String qTitle, final String optOne,
            final String optTwo, final String optThree, final String optFour,
            final String ans) {
        this.questionId = qId;
        this.questionTitle = qTitle;
        this.optionOne = optOne;
        this.optionTwo = optTwo;
        this.optionThree = optThree;
        this.optionFour = optFour;
        this.answer = ans;
    }
}
