package com.project.assesmentportal.dto;

import com.project.assesmentportal.entities.Options;

import lombok.Getter;
import lombok.Setter;

/**
 * questionDto for quiz entity.
 */
@Getter
@Setter
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
     * The options of answer.
     */
    private Options options;

    /**
     * Getter for getting the options.
     * @return Options object.
     */
    public final Options getOptions() {
        return new Options(options.getOptionI(), options.getOptionII(),
                options.getOptionIII(), options.getOptionIV());
    }
    /**
     * Setter for setting the options.
     * @param paramOptions The options of Question.
     */
    public final void setOptions(final Options paramOptions) {
        this.options = new Options(paramOptions.getOptionI(),
                paramOptions.getOptionII(), paramOptions.getOptionIII(),
                paramOptions.getOptionIV());
    }

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
     * default constructor for question.
     */
    public QuestionDto() {
        this.options = new Options("", "", "", "");
    }

    /**
     * all args constructor.
     * @param qId    id of question
     * @param qTitle title of question
     * @param opts options
     * @param ans    answer
     * @param qz     quizDto for question
     */
    public QuestionDto(final long qId, final String qTitle,
            final Options opts, final String ans, final QuizDto qz) {
        this.questionId = qId;
        this.questionTitle = qTitle;
        this.options = new Options(opts.getOptionI(), opts.getOptionII(),
                opts.getOptionIII(), opts.getOptionIV());
        this.answer = ans;
        this.quiz = new QuizDto(qz.getQuizId(), qz.getQuizTitle(),
                qz.getQuizDescription(), qz.getQuizTimer(),
                qz.getCategory());
    }
}