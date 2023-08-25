package com.project.assesmentportal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.assesmentportal.entities.Category;
import com.project.assesmentportal.services.CategoryService;

@CrossOrigin("*")
@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/categories", method =RequestMethod.POST)
	public ResponseEntity<Category> addCategory(@RequestBody Category category ){
		return new ResponseEntity<Category>(categoryService.addCategory(category), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/categories", method =RequestMethod.GET)
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}
	
	@RequestMapping(value="/categories/{id}", method =RequestMethod.GET)
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") long catId){
		return new ResponseEntity<Category>(categoryService.getCategoryById(catId), HttpStatus.OK);
	}
	@RequestMapping(value="/categories/{id}", method =RequestMethod.PUT)
	public ResponseEntity<Category> updateCategory(@PathVariable("id") long catId,@RequestBody Category category){
		return new ResponseEntity<Category>(categoryService.updateCategory(category, catId), HttpStatus.OK);
	}
	
	@RequestMapping(value="/categories/{id}", method =RequestMethod.DELETE)
	public ResponseEntity<String> deleteCategory(@PathVariable("id") long catId){
		categoryService.deleteCategory(catId);
		return new ResponseEntity<String>("Category deleted successfully!", HttpStatus.OK);
	}
	
	
}
