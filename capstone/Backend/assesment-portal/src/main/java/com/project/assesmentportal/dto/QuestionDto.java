package com.project.assesmentportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * questionDto for quiz entity.
 */
@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {
    /**
     * The unique identifier of the question.
     */
    private long questionId;

    /**
     * The title of the question.
     */
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
     * instance of quiz.
     */
    private QuizDto quiz;

    /**
     * getter for quiz.
     * @return quiz belongs to.
     */
    public final QuizDto getQuiz() {
        if (quiz != null) {
            return new QuizDto(quiz.getQuizId(), quiz.getQuizTitle(),
                    quiz.getQuizDescription(), quiz.getQuizTimer(),
                    quiz.getCategory());
        }
        return null;
    }

    /**
     * setter for the quiz.
     * @param qz which the quiz belongs to.
     */
    public final void setQuiz(final QuizDto qz) {
        if (qz != null) {
            this.quiz = new QuizDto(qz.getQuizId(), qz.getQuizTitle(),
                    qz.getQuizDescription(), qz.getQuizTimer(),
                    qz.getCategory());
        } else {
            this.quiz = null;
        }
    }

    /**
     * all args constructor.
     * @param qId    id of question
     * @param qTitle title of question
     * @param opt1   option 1
     * @param opt2   option2
     * @param opt3   option 3
     * @param opt4   option 4
     * @param ans    answer
     * @param qz     quizDto for question
     */
    public QuestionDto(final long qId, final String qTitle,
            final String opt1, final String opt2, final String opt3,
            final String opt4, final String ans, final QuizDto qz) {
        this.questionId = qId;
        this.questionTitle = qTitle;
        this.optionOne = opt1;
        this.optionTwo = opt2;
        this.optionThree = opt3;
        this.optionFour = opt4;
        this.answer = ans;
        this.quiz = new QuizDto(qz.getQuizId(), qz.getQuizTitle(),
                qz.getQuizDescription(), qz.getQuizTimer(),
                qz.getCategory());
    }
}
