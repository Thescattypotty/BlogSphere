package org.blogs.blogsphere.Controllers;

import org.blogs.blogsphere.Payload.Request.CategoryRequest;
import org.blogs.blogsphere.Payload.Response.CategoryResponse;
import org.blogs.blogsphere.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest)
    {
        return new ResponseEntity<>(categoryService.createCategory(categoryRequest),HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories()
    {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryName(@PathVariable("id") Long catId,@RequestBody CategoryRequest categoryRequest)
    {
        return ResponseEntity.ok(categoryService.changeName(catId, categoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId)
    {
        categoryService.deleteCategorie(categoryId);
        return ResponseEntity.ok("Category deleted successfully !");
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Long categoryId)
    {
        return ResponseEntity.ok(categoryService.getCategorybyId(categoryId));
    }
}
