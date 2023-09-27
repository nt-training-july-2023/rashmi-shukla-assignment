package com.project.assesmentportal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    /**
     * The unique identifier of the category.
     */
    private long categoryId;

    /**
     * The title of the category.
     */
    @NotBlank(message = "Category title is required")
    private String categoryTitle;

    /**
     * The description of the category.
     */
    @NotBlank(message = "Category description is required")
    private String categoryDescription;
}
