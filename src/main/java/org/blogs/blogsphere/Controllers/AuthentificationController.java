package org.blogs.blogsphere.Controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.blogs.blogsphere.Entities.RefreshToken;
import org.blogs.blogsphere.Exceptions.TokenRefreshException;
import org.blogs.blogsphere.Payload.Request.LoginRequest;
import org.blogs.blogsphere.Payload.Request.RegisterRequest;
import org.blogs.blogsphere.Payload.Request.TokenRefreshRequest;
import org.blogs.blogsphere.Payload.Response.JwtResponse;
import org.blogs.blogsphere.Payload.Response.MessageResponse;
import org.blogs.blogsphere.Payload.Response.TokenRefreshResponse;
import org.blogs.blogsphere.Security.Jwt.JwtUtils;
import org.blogs.blogsphere.Security.Services.RefreshTokenService;
import org.blogs.blogsphere.Security.Services.UserDetailsImplementation;
import org.blogs.blogsphere.Services.AuthentificationService;
import org.blogs.blogsphere.Services.BadRequetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {
    @Autowired
    private BadRequetService badRequestService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthentificationService authentificationService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authentificationService.login(loginRequest));
    }

    @PostMapping("/signUp")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
        if (badRequestService.checkIfEmailExist(signUpRequest.getEmail()) != null) {
            return ResponseEntity.badRequest().body(
                    badRequestService.checkIfEmailExist(signUpRequest.getEmail()));
        }
        if (badRequestService.checkIfUsernameExist(signUpRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body(
                    badRequestService.checkIfUsernameExist(signUpRequest.getUsername()));
        }

        return ResponseEntity.ok(authentificationService.register(signUpRequest));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImplementation userDetails = (UserDetailsImplementation) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Long userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> GetUser() {
        UserDetailsImplementation userDetails = (UserDetailsImplementation) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(),
                        userDetails.getEmail(), roles));

    }

}
