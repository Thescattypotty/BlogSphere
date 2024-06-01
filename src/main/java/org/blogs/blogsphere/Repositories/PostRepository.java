package org.blogs.blogsphere.Repositories;

import org.blogs.blogsphere.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>
{
    
}
