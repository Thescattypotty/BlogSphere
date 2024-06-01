package org.blogs.blogsphere.Repositories;

import org.blogs.blogsphere.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>
{
    
}
