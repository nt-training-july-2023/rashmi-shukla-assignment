package com.project.assesmentportal.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.services.CategoryService;

import jakarta.validation.Valid;

/**
 * Controller class responsible for handling CRUD operations.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    /**
     * instance of CategoryService.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER
            = LoggerFactory
                    .getLogger(CategoryController.class);

    /**
     * Adds a new category.
     * @param categoryDto The CategoryDto representing the category to be
     *                    added.
     * @return A ResponseEntity containing the added CategoryDto and HTTP
     *         status CREATED (201).
     */
    @PostMapping()
    public final ResponseEntity<String> addCategory(
            @RequestBody @Valid final CategoryDto categoryDto) {
        LOGGER.info("Adding a category method invoked.");
        String response = categoryService.addCategory(categoryDto);
        LOGGER.info("Added a category successfully.");
        return new ResponseEntity<String>(response,
                HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all categories.
     * @return A list of CategoryDto objects representing all categories.
     */
    @GetMapping()
    public final List<CategoryDto> getCategories() {
        LOGGER.info("Retrieving all categories");
        List<CategoryDto> categories = categoryService.getCategories();
        LOGGER.info("Retrieved list of category successfully.");
        return categories;
    }

    /**
     * Retrieves a category by its ID.
     * @param catId The ID of the category to retrieve.
     * @return A ResponseEntity containing the retrieved CategoryDto and
     *         HTTP status OK (200).
     */
    @GetMapping("/{id}")
    public final ResponseEntity<CategoryDto> getCategoryById(
            @PathVariable("id") final long catId) {
        LOGGER.info("Getting a category with ID: " + catId);
        CategoryDto categoryDto = categoryService.getCategoryById(catId);
        LOGGER.info("Retrieved a category successfully with ID: " + catId);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }

    /**
     * Updates a category.
     * @param catId       The ID of the category to update.
     * @param categoryDto The updated CategoryDto.
     * @return A ResponseEntity containing the updated CategoryDto and HTTP
     *         status OK (200).
     */
    @PutMapping("/{id}")
    public final ResponseEntity<String> updateCategory(
            @PathVariable("id") final long catId,
            @RequestBody @Valid final CategoryDto categoryDto) {
        LOGGER.info("Updating a category with ID: " + catId + " invoked.");
        String response = categoryService.updateCategory(categoryDto, catId);
        LOGGER.info("Updated a category with ID: " + catId + " successfully.");
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    /**
     * Deletes a category by its ID.
     * @param catId The ID of the category to delete.
     * @return A ResponseEntity with a success message and HTTP status OK
     *         (200) after deletion.
     */
    @DeleteMapping("/{id}")
    public final ResponseEntity<String> deleteCategory(
            @PathVariable("id") final long catId) {
        LOGGER.info("Deleting a category with ID: " + catId + " invoked.");
        categoryService.deleteCategory(catId);;
        LOGGER.info("Deleted a category with ID: " + catId + " successfully.");
        return new ResponseEntity<String>("Category deleted successfully!",
                HttpStatus.OK);
    }

    /**
     * returns quizzes of a category.
     * @param id categoryId.
     * @return list of quizzes ResponseEntity
     */
    @GetMapping("/{id}/quizzes")
    public final ResponseEntity<List<QuizDto>> getQuizzesById(
            @PathVariable("id") final long id) {
        LOGGER.info("Retrieving list of quizzes by category method invoked.");
        List<QuizDto> quizzes = categoryService.getQuizzesByCategory(id);
        LOGGER.info("Retrieved list of quizzes by category successfully.");
        return new ResponseEntity<List<QuizDto>>(quizzes, HttpStatus.OK);
    }
}
