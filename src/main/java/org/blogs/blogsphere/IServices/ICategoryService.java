package org.blogs.blogsphere.IServices;

import java.util.List;

import org.blogs.blogsphere.Payload.Request.CategoryRequest;
import org.blogs.blogsphere.Payload.Response.CategoryResponse;

public interface ICategoryService {
    CategoryResponse createCategory(CategoryRequest category);
    CategoryResponse getCategorybyId(Long categoryId);
    List<CategoryResponse> getAllCategories();
    CategoryResponse changeName(Long categoryId , CategoryRequest categoryRequest);
    void deleteCategorie(Long categoryId);
}
