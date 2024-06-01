package org.blogs.blogsphere.Mapper.MapToResponse;

import org.blogs.blogsphere.Entities.Category;
import org.blogs.blogsphere.Payload.Response.CategoryResponse;

public class CategorieMapToResponse {

    public static CategoryResponse MapToCategorieResponse(Category category)
    {
        return new CategoryResponse(
            category.getId(), 
            category.getName(), 
            category.getCreatedBy().getUsername(), 
            category.getCreatedAt()
            );
    }
}
