package org.blogsphere.blog.Component;

import java.util.Optional;

import org.blogsphere.blog.Entity.User;
import org.blogsphere.blog.EntityRepository.UserRepository;
import org.blogsphere.blog.Exception.UserNotFoundException;
import org.blogsphere.blog.Service.UserSecurity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditorAwareComponent implements AuditorAware<String> {

    private final UserRepository userRepository;
    private static final ThreadLocal<Boolean> AUDITOR_CALL_IN_PROGRESS = new ThreadLocal<>();

    @Override
    @Transactional
    public Optional<String> getCurrentAuditor() {
        if (Boolean.TRUE.equals(AUDITOR_CALL_IN_PROGRESS.get())) {
            return Optional.empty();
        }
        try {
            AUDITOR_CALL_IN_PROGRESS.set(true);
            return Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getPrincipal)
                    .map(p -> {
                        if (p instanceof String) {
                            User user =  userRepository.findByUsername((String) p)
                                    .orElseThrow(UserNotFoundException::new);
                                return user.getUsername();
                        } else if (p instanceof UserSecurity) {
                            User user =  userRepository.findByUsername(((UserSecurity) p).getUsername())
                                    .orElseThrow(UserNotFoundException::new);
                            return user.getUsername();
                        }
                        return null;
                    });
        } finally {
            AUDITOR_CALL_IN_PROGRESS.remove();
        }
    }
}