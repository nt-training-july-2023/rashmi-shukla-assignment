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

import com.project.assesmentportal.dto.CategoryDto;
import com.project.assesmentportal.services.CategoryService;

@CrossOrigin("*")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public final ResponseEntity<CategoryDto> addCategory(
            @RequestBody final CategoryDto categoryDto) {
        CategoryDto addCategoryDto = this.categoryService
                .addCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(addCategoryDto,
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public final List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public final ResponseEntity<CategoryDto> getCategoryById(
            @PathVariable("id") final long catId) {
        return new ResponseEntity<CategoryDto>(
                categoryService.getCategoryById(catId), HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
    public final ResponseEntity<CategoryDto> updateCategory(
            @PathVariable("id") final long catId,
            @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<CategoryDto>(
                categoryService.updateCategory(categoryDto, catId),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public final ResponseEntity<String> deleteCategory(
            @PathVariable("id") final long catId) {
        categoryService.deleteCategory(catId);
        return new ResponseEntity<String>("Category deleted successfully!",
                HttpStatus.OK);
    }

}
