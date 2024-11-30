package org.blogsphere.blog.EntityRepository;

import java.util.UUID;

import org.blogsphere.blog.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID>{
    
}
