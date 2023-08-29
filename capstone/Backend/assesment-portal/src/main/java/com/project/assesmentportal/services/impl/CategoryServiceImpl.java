package com.project.assesmentportal.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.assesmentportal.entities.Category;
import com.project.assesmentportal.exceptions.DuplicateResourceException;
import com.project.assesmentportal.exceptions.ResourceNotFoundException;
import com.project.assesmentportal.repositories.CategoryRepository;
import com.project.assesmentportal.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		Optional<Category> checkExistingCategory = categoryRepository.findByCategoryTitle(category.getCategoryTitle());
		if(checkExistingCategory.isPresent()) {
			throw new DuplicateResourceException("Category already exists");
		}
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
		if(category.isPresent()) {
			return category.get();
		}
		else {
			throw new ResourceNotFoundException("Category doesnot exists");
		}
	}

	@Override
	public Category updateCategory(Category category, long categoryId) {
		Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category doesnot exists"));
		existingCategory.setCategoryTitle(category.getCategoryTitle());
		existingCategory.setCategoryDescription(category.getCategoryDescription());
		
		categoryRepository.save(existingCategory);
		return existingCategory;
	}

	@Override
	public void deleteCategory(long categoryId) {
		categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category doesnot exists"));
		categoryRepository.deleteById(categoryId);
		
	}

}
