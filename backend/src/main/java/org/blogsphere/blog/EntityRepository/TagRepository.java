package org.blogsphere.blog.EntityRepository;

import java.util.UUID;

import org.blogsphere.blog.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<Tag, UUID>{
    List<Tag> findByCreatedBy(String createdBy);
}
