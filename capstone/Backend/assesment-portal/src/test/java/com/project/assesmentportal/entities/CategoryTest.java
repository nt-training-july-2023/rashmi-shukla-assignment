package com.project.assesmentportal.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {
    Category category;
    
    @BeforeEach
    void init() {
        category = new Category();
    }
    
    @Test
    void testGettersAndSetters() {
        assertEquals(0,category.getCategoryId());
        assertEquals(null, category.getCategoryTitle());
        assertEquals(null, category.getCategoryDescription());
        
        category.setCategoryId(1);
        category.setCategoryTitle("React");
        category.setCategoryDescription("React Description");
        assertEquals(1, category.getCategoryId());
        assertEquals("React", category.getCategoryTitle());
        assertEquals("React Description", category.getCategoryDescription());
    }
    @Test
    void testDefaultConstructor() {
        Category category = new Category();
        assertEquals(0,category.getCategoryId());
        assertEquals(null, category.getCategoryTitle());
        assertEquals(null, category.getCategoryDescription());
    }
    @Test
    void testParameterisedConstructor() {
        Category category = new Category(1,"React","React Description");
        assertEquals(1, category.getCategoryId());
        assertEquals("React", category.getCategoryTitle());
        assertEquals("React Description", category.getCategoryDescription());
    }

}
