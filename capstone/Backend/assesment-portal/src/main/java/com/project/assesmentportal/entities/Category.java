package com.project.assesmentportal.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a category.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    /**
     * The unique identifier of the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    /**
     * The title of the category.
     */
    @Column(name = "cat_title", unique = true, nullable = false)
    private String categoryTitle;

    /**
     * The description of the category.
     */
    @Column(name = "cat_desc")
    private String categoryDescription;
    
    @OneToMany(mappedBy = "category", cascade= CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();
    
    public List<Quiz> getQuizzes() {
        return new ArrayList<>(quizzes);
    }

    public void setQuizzes(final List<Quiz> quizzes) {
        this.quizzes = new ArrayList<>(quizzes);
    }
    
    public Category(final long categoryId, 
            final String categoryTitle,
            final String categoryDesc) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDesc;
    }
 
}
