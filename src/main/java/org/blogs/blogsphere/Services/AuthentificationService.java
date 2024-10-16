package org.blogs.blogsphere.Services;

import org.blogs.blogsphere.Entities.RefreshToken;
import org.blogs.blogsphere.Entities.Role;
import org.blogs.blogsphere.Entities.User;
import org.blogs.blogsphere.Enum.ERole;
import org.blogs.blogsphere.Payload.Request.LoginRequest;
import org.blogs.blogsphere.Payload.Request.RegisterRequest;
import org.blogs.blogsphere.Payload.Response.JwtResponse;
import org.blogs.blogsphere.Payload.Response.MessageResponse;
import org.blogs.blogsphere.Repositories.RoleRepository;
import org.blogs.blogsphere.Repositories.UserRepository;
import org.blogs.blogsphere.Security.Jwt.JwtUtils;
import org.blogs.blogsphere.Security.Services.RefreshTokenService;
import org.blogs.blogsphere.Security.Services.UserDetailsImplementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {
    @Autowired
    private AuthenticationManager authentificationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public MessageResponse register(RegisterRequest signUpRequest) {
        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                signUpRequest.getPassword());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                switch (role) {
                    case "admin":
                        roles.add(adminRole);
                        roles.add(userRole);
                        break;
                    default:
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new MessageResponse("User Registred Successfully");
    }

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authentificationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), roles);
    }

}
