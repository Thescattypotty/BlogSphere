package org.blogsphere.blog.EntityRepository;

import java.util.UUID;

import org.blogsphere.blog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{
    
}
