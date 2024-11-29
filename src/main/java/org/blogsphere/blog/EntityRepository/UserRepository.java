package org.blogsphere.blog.EntityRepository;

import java.util.Optional;

import org.blogsphere.blog.Entity.User;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByUsername(String username);
}
