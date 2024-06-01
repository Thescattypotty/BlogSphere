package org.blogs.blogsphere.Mapper.MapToRequest;

import org.blogs.blogsphere.Entities.Category;
import org.blogs.blogsphere.Payload.Request.CategoryRequest;

public class CategorieMapFromRequest {
    
    public static Category mapToCategory(CategoryRequest categoryRequest)
    {
        return new Category(
            categoryRequest.getName()
        );
    }
}
