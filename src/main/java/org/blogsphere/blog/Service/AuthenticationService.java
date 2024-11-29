package org.blogsphere.blog.Service;

import org.blogsphere.blog.Entity.User;
import org.blogsphere.blog.EntityRepository.UserRepository;
import org.blogsphere.blog.Exception.InvalidCredentialsException;
import org.blogsphere.blog.Exception.UserNotFoundException;
import org.blogsphere.blog.IService.IAuthenticationService;
import org.blogsphere.blog.Payload.Request.LoginRequest;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Payload.Response.JwtResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService{
    
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtUtilService jwtUtilService;

    @Override
    public void register(RegisterRequest registerRequest) {
        userService.createUser(registerRequest);
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }
        

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        User user = userRepository.findByUsername(userSecurity.getUsername())
            .orElseThrow(() -> new UserNotFoundException());

        String accessToken = jwtUtilService.generateToken(user.getUsername(), user.getRoles(), "ACCESS");
        String refreshToken = jwtUtilService.generateToken(user.getUsername(), user.getRoles(), "REFRESH");

        return new JwtResponse(
            "Bearer",
            accessToken,
            refreshToken
        );
    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }
    
}
