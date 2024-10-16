package org.blogs.blogsphere.Repositories;

import java.util.Optional;

import org.blogs.blogsphere.Entities.RefreshToken;
import org.blogs.blogsphere.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>
{
    Optional<RefreshToken> findByToken(String token);
    @Modifying
    int deleteByUser(User user);
    Optional<RefreshToken> findByUserId(Long userId);
}
