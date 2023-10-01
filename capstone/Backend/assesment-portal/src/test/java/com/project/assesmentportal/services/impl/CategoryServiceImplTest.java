package com.project.assesmentportal.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import com.project.assesmentportal.dto.ApiResponse;
import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.dto.QuizDto;
import com.project.assesmentportal.entities.Category;
import com.project.assesmentportal.entities.Quiz;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.messages.MessageConstants;
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
        
        ApiResponse result = categoryServiceImpl.addCategory(categoryDto);
        assertNotNull(result);
        assertEquals(MessageConstants.CATEGORY_ADDED_SUCCESSFULLY, result.getMessage());
        assertEquals(HttpStatus.CREATED.value(), result.getStatus());
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
    
    @Test
    void testGetCategoryById_CategoryNotFound() {
        Category category = new Category();
        category.setCategoryId(1);
        category.setCategoryTitle("React");
        category.setCategoryDescription("Mcqs");
        
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            categoryServiceImpl.getCategoryById(1);
        });
        
    }
    
    @Test
    public void testGetCategories() {
        List<Category> catList = new ArrayList<>();
        catList.add(new Category(1,"React","Mcq"));
        catList.add(new Category(2,"Java","Mcq"));

        when(categoryRepository.findAll()).thenReturn(catList);
        List<CategoryDto> categoryDtos = categoryServiceImpl.getCategories();

        assertNotNull(categoryDtos);
        assertEquals(2, categoryDtos.size()); // Assuming there are 2 users in the list
    }
    
    @Test
    void testUpdateCategory_Success() {
        long categoryIdToUpdate = 1L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryTitle("React");
        categoryDto.setCategoryDescription("Mcqs");
        
        Category existingCategory = new Category();
        existingCategory.setCategoryId(categoryIdToUpdate);
        existingCategory.setCategoryTitle("Java");
        existingCategory.setCategoryDescription("mcqs");
        
        when(modelMapper.map(existingCategory, CategoryDto.class)).thenReturn(categoryDto);
        when(categoryRepository.findById(categoryIdToUpdate)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.findByCategoryTitle(categoryDto.getCategoryTitle())).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);
        
        ApiResponse result = categoryServiceImpl.updateCategory(categoryDto,categoryIdToUpdate);
        assertNotNull(result);
        assertEquals(MessageConstants.CATEGORY_UPDATED_SUCCESSFULLY, result.getMessage());
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        
    }
    
    @Test
    void testUpdateCategory_ResourceNotFound() {
        long categoryIdToUpdate = 1L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryTitle("React");
        categoryDto.setCategoryDescription("Mcqs");
        
        Category existingCategory = new Category();
        existingCategory.setCategoryId(categoryIdToUpdate);
        existingCategory.setCategoryTitle("Java");
        existingCategory.setCategoryDescription("mcqs");
        
        when(categoryRepository.findById(categoryIdToUpdate)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            categoryServiceImpl.updateCategory(categoryDto, 1);
        });
        
    }
    
    @Test
    void testUpdateCategory_DuplicateEmail() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryTitle("Java");
        
        long categoryIdToUpdate = 1L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryTitle("React");
        categoryDto.setCategoryDescription("Mcqs");
        
        Category existingCategory = new Category();
        existingCategory.setCategoryId(2L);
        existingCategory.setCategoryTitle("React");
        existingCategory.setCategoryDescription("mcqs");
        
        when(categoryRepository.findById(categoryIdToUpdate)).thenReturn(Optional.of(category));
        when(categoryRepository.findByCategoryTitle(categoryDto.getCategoryTitle())).thenReturn(Optional.of(existingCategory));

        
        assertThrows(DuplicateResourceException.class, () -> {
            categoryServiceImpl.updateCategory(categoryDto, 1);
        });
        
    }
    
    @Test
    public final void testDeleteCategory_Success() {
        long categoryIdToDelete = 1L;
        Category categoryToDelete = new Category();
        categoryToDelete.setCategoryId(categoryIdToDelete);

        // Mock the behavior of the repository
        when(categoryRepository.findById(categoryIdToDelete)).thenReturn(Optional.of(categoryToDelete));

        // Act
        ApiResponse response = categoryServiceImpl.deleteCategory(categoryIdToDelete);

        // Assert
        verify(categoryRepository, times(1)).deleteById(categoryIdToDelete);
        assertEquals(MessageConstants.CATEGORY_DELETED_SUCCESSFULLY, response.getMessage());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    
    @Test
    public final void testDeleteCategory_NotFound() {
        long categoryIdToDelete = 1L;

        // Mock the behavior of the repository to return an empty Optional
        when(categoryRepository.findById(categoryIdToDelete)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            categoryServiceImpl.deleteCategory(categoryIdToDelete);
        });
    }

    @Test
    public final void testGetQuizzesByCategory_Success() {
        long catId = 1L;
        Category category = new Category();
        category.setCategoryId(catId);
        category.setCategoryTitle("React");
        category.setCategoryDescription("Mcqs");
        Quiz quiz = new Quiz(1, "React", "descr", 20, category);
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(quiz);
        category.setQuizzes(quizList);
        
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryTitle(category.getCategoryTitle());
        categoryDto.setCategoryDescription(category.getCategoryDescription());
        QuizDto quizDto = new QuizDto(1, "React", "descr", 20, null);
        
        when(categoryRepository.findById(catId)).thenReturn(Optional.of(category));
        when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
        when(modelMapper.map(quiz.getCategory(), CategoryDto.class)).thenReturn(categoryDto);
        
        List<QuizDto> quizDtos = categoryServiceImpl.getQuizzesByCategory(catId);
        
        assertNotNull(quizDtos);
        assertEquals(1, quizDtos.size());
    }

}
