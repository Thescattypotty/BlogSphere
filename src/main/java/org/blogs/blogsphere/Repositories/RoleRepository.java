package org.blogs.blogsphere.Repositories;

import java.util.Optional;

import org.blogs.blogsphere.Entities.Role;
import org.blogs.blogsphere.Enum.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(ERole name);
}
