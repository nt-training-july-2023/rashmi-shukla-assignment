package com.project.assesmentportal.dto;

import org.antlr.v4.runtime.misc.NotNull;

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
    private String categoryTitle;

    /**
     * The description of the category.
     */
    private String categoryDescription;
}
