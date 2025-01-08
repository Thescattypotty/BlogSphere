package org.blogsphere.blog.IService;

import java.util.List;

import org.blogsphere.blog.Enum.ERole;
import org.blogsphere.blog.Payload.Request.ChangePasswordRequest;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Payload.Request.UserRequest;
import org.blogsphere.blog.Payload.Response.UserResponse;

public interface IUserService {

    void createUser(RegisterRequest registerRequest);
    
    void updateUser(String id, UserRequest userRequest);
    
    UserResponse getUserById(String id);  
    
    UserResponse getUserByUsername(String username);
    
    List<UserResponse> findUsers();

    void deleteUser(String id);

    void addRole(String id, ERole role);

    void removeRole(String id, ERole role);

    void updatePassword(String id , ChangePasswordRequest changePasswordRequest);
}
