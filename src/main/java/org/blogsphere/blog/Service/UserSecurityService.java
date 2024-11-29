package org.blogsphere.blog.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.blogsphere.blog.Entity.User;
import org.blogsphere.blog.EntityRepository.UserRepository;
import org.blogsphere.blog.Enum.ERole;
import org.blogsphere.blog.Exception.UserNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException());
        
        return UserSecurity.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .password(user.getPassword())
            .authorities(mapGrantedAuthority(user.getRoles()))
            .build();
    }

    private List<SimpleGrantedAuthority> mapGrantedAuthority(Set<ERole> roles) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (ERole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }
    
}
