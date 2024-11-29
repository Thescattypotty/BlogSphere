package org.blogsphere.blog.IService;

import org.blogsphere.blog.Payload.Request.LoginRequest;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Payload.Response.JwtResponse;

public interface IAuthenticationService {
    void register(RegisterRequest registerRequest);
    JwtResponse login(LoginRequest loginRequest);
    void logout();
}
