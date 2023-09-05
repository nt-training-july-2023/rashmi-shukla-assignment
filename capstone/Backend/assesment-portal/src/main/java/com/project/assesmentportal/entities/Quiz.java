package com.project.assesmentportal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "quizes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;
    
    @Column(name= "quiz_title",nullable = false, unique = true)
    private String quizTitle;
    
    @Column(name="quiz_desc")
    private String quizDescription;
    
    private int quizTimer;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    
    public Category getCategory() {
        return new Category(category.getCategoryId(), 
                category.getCategoryTitle(),
                category.getCategoryDescription());
    }
    
    public void setCategory(final Category category) {
        this.category = new Category(category.getCategoryId(),
                category.getCategoryTitle(),
                category.getCategoryDescription());
    }
    
    public Quiz(final long quizId,
            final String quizTitle,
            final String quizDesc,
            final int time) {
        this.quizId = quizId;
        this.quizTitle= quizTitle;
        this.quizDescription = quizDesc;
        this.quizTimer = time;
    }
}
