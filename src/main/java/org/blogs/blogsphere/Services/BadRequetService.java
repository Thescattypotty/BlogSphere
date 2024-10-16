package org.blogs.blogsphere.Services;

import org.blogs.blogsphere.Payload.Response.MessageResponse;
import org.blogs.blogsphere.Repositories.UserRepository;
import org.blogs.blogsphere.Security.Services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadRequetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public MessageResponse checkIfUsernameExist(String username) {
        if (userRepository.existsByUsername(username)) {

            return new MessageResponse("Error:" + username + "is already taken!");
        }
        return null;
    }

    public MessageResponse checkIfEmailExist(String email) {
        if (userRepository.existsByEmail(email)) {

            return new MessageResponse("Error:" + email + "is already taken!");
        }
        return null;
    }
}