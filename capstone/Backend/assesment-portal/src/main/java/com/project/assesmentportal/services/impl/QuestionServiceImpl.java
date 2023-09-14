package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.dto.QuestionDto;
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.entities.Category;
import com.project.assesmentportal.entities.Question;
import com.project.assesmentportal.entities.Quiz;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.repositories.QuestionRepository;
import com.project.assesmentportal.services.QuestionService;

/**
 * Implementation of the QuizService interface for managing
 * question-related operations.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    /**
     * instance of ModelMapper.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * instance of QuestionRepositoy.
     */
    @Autowired
    private QuestionRepository questionRepository;

    /**
     * add new question.
     * @param questionDto QuestionDto of question.
     * @return returns QuestionDto of added question.
     */
    @Override
    public final QuestionDto addQuestion(final QuestionDto questionDto) {
        System.out.println(questionDto);
        Question question = this.dtoToEntity(questionDto);
        System.out.println(question);
        Question savedQuestion = questionRepository.save(question);
        return this.entityToDto(savedQuestion);
    }

    /**
     * gets all questions.
     * @return list of questions
     */
    @Override
    public final List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDto> questionDtos = questions.stream()
                .map(this::entityToDto).collect(Collectors.toList());
        return questionDtos;
    }

    /**
     * updates existing question.
     * @param  questionDto QuestionDto of quiz
     * @param questionId Question id of existing quiz.
     * @return updated quiz.
     */
    @Override
    public final QuestionDto updateQuestion(final QuestionDto questionDto,
            final long questionId) {
        Question exisitingQuestion = questionRepository
                .findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Question doesnot exists"));

        Question question = dtoToEntity(questionDto);

        exisitingQuestion.setQuestionTitle(question.getQuestionTitle());
        exisitingQuestion.setOptionOne(question.getOptionOne());
        exisitingQuestion.setOptionTwo(question.getOptionTwo());
        exisitingQuestion.setOptionThree(question.getOptionThree());
        exisitingQuestion.setOptionFour(question.getOptionFour());
        exisitingQuestion.setAnswer(question.getAnswer());
        exisitingQuestion.setQuiz(question.getQuiz());

        Question updatedQuestion = questionRepository
                .save(exisitingQuestion);
        return this.entityToDto(updatedQuestion);
    }

    /**
     * get question by its id.
     * @param questionId id of the question
     * return question dto.
     */
    @Override
    public final QuestionDto getQuestionById(final long questionId) {
        Question question = questionRepository.findById(questionId).get();
        return this.entityToDto(question);
    }

    /**
     * deletes question by id.
     * @param questionId id of the question to delete.
     */
    @Override
    public final void deleteQuestion(final long questionId) {
        questionRepository.deleteById(questionId);
    }

    /**
     * converts dto to entity.
     * @param questionDto QuestionDto to convert
     * @return converted question.
     */
    public final Question dtoToEntity(final QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);
        if (questionDto.getQuiz() != null) {
            Quiz quiz = modelMapper.map(questionDto.getQuiz(), Quiz.class);
            if (questionDto.getQuiz().getCategory() != null) {
                Category category = modelMapper.map(
                        questionDto.getQuiz().getCategory(),
                        Category.class);
                quiz.setCategory(category);
            }
            question.setQuiz(quiz);
        }
        return question;
    }

    /**
     * converts question to questionDto.
     * @param question Question entity.
     * @return questionDto.
     */
    public final QuestionDto entityToDto(final Question question) {
        // Map the QuizDto to a Quiz entity
        QuestionDto questionDto = modelMapper.map(question,
                QuestionDto.class);
        if (question.getQuiz() != null) {
            QuizDto quizDto = modelMapper.map(question.getQuiz(),
                    QuizDto.class);
            if (question.getQuiz().getCategory() != null) {
                CategoryDto categoryDto = modelMapper.map(
                        question.getQuiz().getCategory(),
                        CategoryDto.class);
                quizDto.setCategory(categoryDto);
            }
            questionDto.setQuiz(quizDto);
        }
        return questionDto;
    }

}
