package org.blogs.blogsphere.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.blogs.blogsphere.Entities.Category;
import org.blogs.blogsphere.Exceptions.ResourceNotFoundException;
import org.blogs.blogsphere.IServices.ICategoryService;
import org.blogs.blogsphere.Mapper.MapToRequest.CategorieMapFromRequest;
import org.blogs.blogsphere.Mapper.MapToResponse.CategorieMapToResponse;
import org.blogs.blogsphere.Payload.Request.CategoryRequest;
import org.blogs.blogsphere.Payload.Response.CategoryResponse;
import org.blogs.blogsphere.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse changeName(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find Categorie"));
        category.setName(categoryRequest.getName());
        return CategorieMapToResponse.MapToCategorieResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest category) {
        return CategorieMapToResponse.MapToCategorieResponse(categoryRepository.save(CategorieMapFromRequest.mapToCategory(category)));
    }

    @Override
    public void deleteCategorie(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find Categorie"));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
         List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No categories found");
        }
        return categories.stream()
            .map(CategorieMapToResponse::MapToCategorieResponse)
            .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategorybyId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Cannot find Categorie"));
        
        return CategorieMapToResponse.MapToCategorieResponse(category);
    }

    
}
