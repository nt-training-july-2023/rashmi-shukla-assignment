package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.dto.CategoryDto;

/**
 * Service interface for managing category-related operations.
 */
public interface CategoryService {

    /**
     * Adds a new category.
     *
     * @param categoryDto The CategoryDto representing the category to be added.
     * @return The CategoryDto of the added category.
     */
    CategoryDto addCategory(CategoryDto categoryDto);

    /**
     * Retrieves a list of all categories.
     *
     * @return A list of CategoryDto objects representing all categories.
     */
    List<CategoryDto> getAllCategories();

    /**
     * Retrieves a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The CategoryDto of the retrieved category.
     */
    CategoryDto getCategoryById(long categoryId);

    /**
     * Updates a category.
     *
     * @param categoryDto The updated CategoryDto.
     * @param categoryId  The ID of the category to update.
     * @return The CategoryDto of the updated category.
     */
    CategoryDto updateCategory(CategoryDto categoryDto, long categoryId);

    /**
     * Deletes a category by its ID.
     *
     * @param categoryId The ID of the category to delete.
     */
    void deleteCategory(long categoryId);
}
