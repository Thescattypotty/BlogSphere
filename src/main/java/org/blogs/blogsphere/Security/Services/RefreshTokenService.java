package org.blogs.blogsphere.Security.Services;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.blogs.blogsphere.Entities.RefreshToken;
import org.blogs.blogsphere.Entities.User;
import org.blogs.blogsphere.Repositories.RefreshTokenRepository;
import org.blogs.blogsphere.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.blogs.blogsphere.Exceptions.TokenRefreshException;

@Service
public class RefreshTokenService {
    @Value("${backend.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(userId);

            if (optionalRefreshToken.isPresent()) {
                RefreshToken refreshToken = updateRefreshToken(userId);
                return refreshToken;
            } else {
                RefreshToken refreshToken = new RefreshToken();
                refreshToken.setUser(optionalUser.get());
                refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
                refreshToken.setToken(UUID.randomUUID().toString());

                return refreshTokenRepository.save(refreshToken);
            }
        } else {
            throw new IllegalArgumentException("User not found for ID: " + userId);
        }
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(),
                    "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public RefreshToken updateRefreshToken(Long userId) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(userId);

        if (optionalRefreshToken.isPresent()) {
            RefreshToken refreshToken = optionalRefreshToken.get();
            refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
            refreshToken.setToken(UUID.randomUUID().toString());
            return refreshTokenRepository.save(refreshToken);
        } else {
            throw new TokenRefreshException("null", "Refresh token not found for user with ID: " + userId);
        }
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
