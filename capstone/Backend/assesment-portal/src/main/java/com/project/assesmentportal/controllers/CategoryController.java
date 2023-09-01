package com.project.assesmentportal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.services.CategoryService;

/**
 * Controller class responsible for handling CRUD operations.
 */
@CrossOrigin("*")
@RestController
public class CategoryController {

    /**
     * instance of CategoryService.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Adds a new category.
     * @param categoryDto The CategoryDto representing the category to be
     *                    added.
     * @return A ResponseEntity containing the added CategoryDto and HTTP
     *         status CREATED (201).
     */
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public final ResponseEntity<CategoryDto> addCategory(
            @RequestBody final CategoryDto categoryDto) {
        CategoryDto addCategoryDto = this.categoryService
                .addCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(addCategoryDto,
                HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all categories.
     * @return A list of CategoryDto objects representing all categories.
     */
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public final List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Retrieves a category by its ID.
     * @param catId The ID of the category to retrieve.
     * @return A ResponseEntity containing the retrieved CategoryDto and
     *         HTTP status OK (200).
     */
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public final ResponseEntity<CategoryDto> getCategoryById(
            @PathVariable("id") final long catId) {
        return new ResponseEntity<CategoryDto>(
                categoryService.getCategoryById(catId), HttpStatus.OK);
    }

    /**
     * Updates a category.
     * @param catId The ID of the category to update.
     * @param categoryDto The updated CategoryDto.
     * @return A ResponseEntity containing the updated CategoryDto and HTTP
     *         status OK (200).
     */
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
    public final ResponseEntity<CategoryDto> updateCategory(
            @PathVariable("id") final long catId,
            @RequestBody final CategoryDto categoryDto) {
        return new ResponseEntity<CategoryDto>(
        categoryService.updateCategory(categoryDto, catId), HttpStatus.OK);
    }

    /**
     * Deletes a category by its ID.
     * @param catId The ID of the category to delete.
     * @return A ResponseEntity with a success message and HTTP status OK
     *         (200) after deletion.
     */
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public final ResponseEntity<String> deleteCategory(
            @PathVariable("id") final long catId) {
        categoryService.deleteCategory(catId);
        return new ResponseEntity<String>("Category deleted successfully!",
                HttpStatus.OK);
    }

}
