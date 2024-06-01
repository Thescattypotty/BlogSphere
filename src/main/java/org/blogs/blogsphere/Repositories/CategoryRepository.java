package org.blogs.blogsphere.Repositories;

import org.blogs.blogsphere.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>
{
    
}
