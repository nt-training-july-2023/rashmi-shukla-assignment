package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.entities.Category;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.repositories.CategoryRepository;
import com.project.assesmentportal.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public final CategoryDto addCategory(final CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Optional<Category> checkExistingCategory = categoryRepository
                .findByCategoryTitle(category.getCategoryTitle());
        if (checkExistingCategory.isPresent()) {
            throw new DuplicateResourceException("Category already exists");
        }

        Category savedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public final List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map((category) -> this.modelMapper.map(category,
                        CategoryDto.class))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public final CategoryDto getCategoryById(final long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return this.modelMapper.map(category.get(), CategoryDto.class);
        } else {
            throw new ResourceNotFoundException("Category doesnot exists");
        }
    }

    @Override
    public final CategoryDto updateCategory(final CategoryDto categoryDto,
            final long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category doesnot exists"));
        existingCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        existingCategory
                .setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public final void deleteCategory(final long categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category doesnot exists"));
        categoryRepository.deleteById(categoryId);

    }

}
