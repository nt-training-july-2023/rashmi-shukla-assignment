package com.project.assesmentportal.services;

import java.util.List;

import com.project.assesmentportal.entities.Category;

public interface CategoryService {
	Category addCategory(Category category);
	List<Category> getAllCategories();
	Category getCategoryById(long categoryId);
	Category updateCategory(Category category, long categoryId);
	void deleteCategory(long categoryId);
}
