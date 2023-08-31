package com.project.assesmentportal.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryDtoTest {

    CategoryDto categoryDto;
    @BeforeEach
    void init() {
        categoryDto = new CategoryDto();
    }
    @Test
    void testGettersAndSetters() {
        assertEquals(0,categoryDto.getCategoryId());
        assertEquals(null, categoryDto.getCategoryTitle());
        assertEquals(null, categoryDto.getCategoryDescription());
        
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryTitle("GK");
        categoryDto.setCategoryDescription("GK Description");
        assertEquals(1, categoryDto.getCategoryId());
        assertEquals("GK", categoryDto.getCategoryTitle());
        assertEquals("GK Description", categoryDto.getCategoryDescription());
    }
    @Test
    void testDefaultConstructor() {
        CategoryDto categoryDto = new CategoryDto();
        assertEquals(0,categoryDto.getCategoryId());
        assertEquals(null, categoryDto.getCategoryTitle());
        assertEquals(null, categoryDto.getCategoryDescription());
    }
    @Test
    void testParameterisedConstructor() {
        CategoryDto categoryDto = new CategoryDto(1,"GK","GK Category");
        assertEquals(1, categoryDto.getCategoryId());
        assertEquals("GK", categoryDto.getCategoryTitle());
        assertEquals("GK Category", categoryDto.getCategoryDescription());
    }

}
