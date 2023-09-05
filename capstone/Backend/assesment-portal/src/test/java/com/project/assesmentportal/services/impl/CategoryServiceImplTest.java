package com.project.assesmentportal.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.entities.Category;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.repositories.CategoryRepository;

class CategoryServiceImplTest {
    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Mock
    private CategoryRepository categoryRepository;
    
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testAddCategory_Success() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryTitle("React");
        categoryDto.setCategoryDescription("Mcqs");
        
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
        when(categoryRepository.findByCategoryTitle(category.getCategoryTitle())).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        
        CategoryDto resultCategoryDto = categoryServiceImpl.addCategory(categoryDto);
        assertNotNull(resultCategoryDto);
        assertEquals(resultCategoryDto.getCategoryId(), category.getCategoryId());
        assertEquals(resultCategoryDto.getCategoryTitle(), resultCategoryDto.getCategoryTitle());
        assertEquals(resultCategoryDto.getCategoryDescription(), resultCategoryDto.getCategoryDescription());
        
    }
    
    @Test
    void testAddCategory_DuplicateResourceException() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryTitle("React");
        categoryDto.setCategoryDescription("Mcqs");
        
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        
        when(modelMapper.map(categoryDto, Category.class)).thenReturn(category);
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
        when(categoryRepository.findByCategoryTitle(category.getCategoryTitle())).thenReturn(Optional.of(category));
        
        assertThrows(DuplicateResourceException.class, () -> {
            categoryServiceImpl.addCategory(categoryDto);
        });
        
    }
    
    @Test
    void testGetCategoryById_Success() {
        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryTitle("React");
        category.setCategoryDescription("Mcqs");
        
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDescription(category.getCategoryDescription());
        
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        
        CategoryDto resultCategoryDto = categoryServiceImpl.getCategoryById(1);
        assertNotNull(resultCategoryDto);
        assertEquals(resultCategoryDto.getCategoryId(), category.getCategoryId());
        assertEquals(resultCategoryDto.getCategoryTitle(), resultCategoryDto.getCategoryTitle());
        assertEquals(resultCategoryDto.getCategoryDescription(), resultCategoryDto.getCategoryDescription());
        
    }

}
