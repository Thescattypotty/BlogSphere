package org.blogsphere.blog.Component;

import java.util.Optional;

import org.blogsphere.blog.Entity.User;
import org.blogsphere.blog.EntityRepository.UserRepository;
import org.blogsphere.blog.Exception.UserNotFoundException;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditorAwareComponent implements AuditorAware<User>{

    private final UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            username = (String) principal;
        } else {
            return Optional.empty();
        }

        return Optional.of(
            userRepository.findByUsername(username)
            .orElseThrow(UserNotFoundException::new)
            );
    }
    
}
