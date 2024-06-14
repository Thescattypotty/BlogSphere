package org.blogs.blogsphere.Security.Authorization;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.blogs.blogsphere.Annotation.RequiresRole;
import org.blogs.blogsphere.Exceptions.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class RoleAuthorizationAspect {

    @Before("@annotation(requiresRole)")
    public void checkRole(JoinPoint joinPoint, RequiresRole requiresRole) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !hasRequiredRole(authentication, requiresRole.value())) {
            throw new AccessDeniedException("Access denied");
        }
    }

    private boolean hasRequiredRole(Authentication authentication, String[] requiredRoles) {
        for (String role : requiredRoles) {
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(role))) {
                return true;
            }
        }
        return false;
    }
}