package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.entities.Category;
import com.project.assesmentportal.entities.Quiz;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.repositories.CategoryRepository;
import com.project.assesmentportal.services.CategoryService;

/**
 * Implementation of the CategoryService interface for managing
 * category-related operations.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * instance of Modelmapper.
     */
    @Autowired
    private ModelMapper modelMapper;
    /**
     * instance of CategoryRepository interface.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * Adds a new category.
     * @param categoryDto The CategoryDto representing the category to be
     *                    added.
     * @return The CategoryDto of the added category.
     * @throws DuplicateResourceException If a category with the same title
     *                                    already exists.
     */
    @Override
    public final String addCategory(final CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto,
                Category.class);
        Optional<Category> checkExistingCategory = categoryRepository
                .findByCategoryTitle(category.getCategoryTitle());
        if (checkExistingCategory.isPresent()) {
            LOGGER.error("AddCategory error: category already exists");
            throw new DuplicateResourceException(
                    "Category already exists");
        }

        Category savedCategory = this.categoryRepository.save(category);
        LOGGER.info("Category has been added successfully.");
        return "Category: " + savedCategory.getCategoryTitle()
                + ", added successfully!";
    }

    /**
     * Retrieves a list of all categories.
     * @return A list of CategoryDto objects representing all categories.
     */
    @Override
    public final List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map((category) -> this.modelMapper.map(category,
                        CategoryDto.class))
                .collect(Collectors.toList());
        LOGGER.info("Retrieved a list of categories successfully.");
        return categoryDtos;
    }

    /**
     * Retrieves a category by its ID.
     * @param categoryId The ID of the category to retrieve.
     * @return The CategoryDto of the retrieved category.
     * @throws ResourceNotFoundException If the category with the specified
     *                                   ID does not exist.
     */
    @Override
    public final CategoryDto getCategoryById(final long categoryId) {
        Optional<Category> category = categoryRepository
                .findById(categoryId);
        if (category.isPresent()) {
            LOGGER.info("Retrieved a category with ID: " + categoryId + " successfully.");
            return this.modelMapper.map(category.get(), CategoryDto.class);
        } else {
            LOGGER.error("Get Category: category not found" + categoryId);
            throw new ResourceNotFoundException("Category doesnot exists");
        }
    }

    /**
     * Updates a category.
     * @param categoryDto The updated CategoryDto.
     * @param categoryId  The ID of the category to update.
     * @return The CategoryDto of the updated category.
     * @throws ResourceNotFoundException If the category with the specified
     *                                   ID does not exist.
     */
    @Override
    public final String updateCategory(final CategoryDto categoryDto,
            final long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseGet(() -> {
                    LOGGER.error("Update Category: category not found" + categoryId);
                    throw new ResourceNotFoundException("Category doesnot exists");
                    });

        // Check if the updated title is the same as the existing one
        if (!categoryDto.getCategoryTitle()
                .equals(existingCategory.getCategoryTitle())) {
            // Title has changed, check for duplicates
            Optional<Category> checkExistingCatgeory = categoryRepository
                    .findByCategoryTitle(categoryDto.getCategoryTitle());
            if (checkExistingCatgeory.isPresent()) {
                LOGGER.error("Update Category: category already exists" + categoryId);
                throw new DuplicateResourceException(
                        "Category with the same title already exists");
            }
        }

        existingCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        existingCategory.setCategoryDescription(
                categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepository
                .save(existingCategory);
        LOGGER.info("Category with ID: " + categoryId + " updated successfully.");
        return "Category: " + updatedCategory.getCategoryTitle()
                + ", updated successfully!";
    }

    /**
     * Deletes a category by its ID.
     * @param categoryId The ID of the category to delete.
     * @throws ResourceNotFoundException If the category with the specified
     *                                   ID doesnot exist.
     */
    @Override
    public final void deleteCategory(final long categoryId) {
        categoryRepository.findById(categoryId)
                .orElseGet(() ->{
                    LOGGER.error("Update Category: category already exists" + categoryId);
                    throw new ResourceNotFoundException(
                        "Category doesnot exists");
                    });
        categoryRepository.deleteById(categoryId);
        LOGGER.info("Category with ID: " + categoryId + " deleted successfully.");

    }

    /**
     * gets quizzes of this category.
     * @param categoryId id of the category.
     * @return list of quizzes.
     */
    @Override
    public final List<QuizDto> getQuizzesByCategory(
            final long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    LOGGER.error("Get Quiz By Category: category not found" + categoryId);
                    throw new ResourceNotFoundException("Category doesn't exists");
                    });
        List<Quiz> quizzes = category.getQuizzes();
        LOGGER.info("Retrieved a list of quiz by category successfully.");
        return quizzes.stream().map(this::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * converts quizDto to quiz.
     * @param quiz Quiz to be converted.
     * @return quizDto.
     */
    public final QuizDto entityToDto(final Quiz quiz) {
        // Map the Quiz entity to a QuizDto
        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        // Map the Category entity to a CategoryDto
        if (quiz.getCategory() != null) {
            CategoryDto categoryDto = modelMapper.map(quiz.getCategory(),
                    CategoryDto.class);
            quizDto.setCategory(categoryDto);
        }
        return quizDto;
    }
}
