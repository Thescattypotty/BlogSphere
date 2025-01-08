package org.blogsphere.blog.Controller;

import java.util.List;

import org.blogsphere.blog.Enum.ERole;
import org.blogsphere.blog.Payload.Request.ChangePasswordRequest;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Payload.Request.UserRequest;
import org.blogsphere.blog.Payload.Response.UserResponse;
import org.blogsphere.blog.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid RegisterRequest registerRequest){
        userService.createUser(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id , @RequestBody @Valid UserRequest userRequest){
        userService.updateUser(id, userRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.findUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping("/role/{id}")
    public ResponseEntity<Void> addRoleToUser(@PathVariable("id") String id , @RequestBody ERole role){
        userService.addRole(id, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<Void> removeRoleToUser(@PathVariable("id") String id, @RequestBody ERole role) {
        userService.addRole(id, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/changepassword/{id}")
    public ResponseEntity<Void> changePassword(@PathVariable("id") String id , @RequestBody @Valid ChangePasswordRequest changePasswordRequest){
        userService.updatePassword(id, changePasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
