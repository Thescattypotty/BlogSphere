package org.blogsphere.blog.EntityRepository;

import java.util.UUID;

import org.blogsphere.blog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t.id = :tagId")
    List<Post> findByTagsId(@Param("tagId") UUID tagId);
}
