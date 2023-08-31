package com.project.assesmentportal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.assesmentportal.entities.Category;

/**
 * JPA repository interface for managing Category entities.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Retrieves a category by its title.
     * @param categoryTitle The title of the category to retrieve.
     * @return An Optional containing the retrieved Category entity, if found.
     */
    Optional<Category> findByCategoryTitle(String categoryTitle);
}
