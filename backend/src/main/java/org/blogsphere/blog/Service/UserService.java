package org.blogsphere.blog.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.blogsphere.blog.Entity.User;
import org.blogsphere.blog.EntityRepository.UserRepository;
import org.blogsphere.blog.Enum.ERole;
import org.blogsphere.blog.Exception.PasswordDoesntMatchException;
import org.blogsphere.blog.Exception.UserNotFoundException;
import org.blogsphere.blog.IService.IUserService;
import org.blogsphere.blog.Payload.Mapper.UserMapper;
import org.blogsphere.blog.Payload.Request.ChangePasswordRequest;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Payload.Request.UserRequest;
import org.blogsphere.blog.Payload.Response.UserResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void createUser(RegisterRequest registerRequest) {
        User user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(String id, UserRequest userRequest) {
        User user = userRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new UserNotFoundException());

        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setProfilePicture(userRequest.profilePicture());
        user.setBio(userRequest.bio());
        
        userRepository.save(user);
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public UserResponse getUserById(String id) {
        return userRepository.findById(UUID.fromString(id))
            .map(userMapper::toUserResponse)
            .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    @Cacheable(value = "user", key = "#username")
    public UserResponse getUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .map(userMapper::toUserResponse)
            .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    @Cacheable(value = "users")
    public List<UserResponse> findUsers() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toUserResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUser(String id) {

        if(userRepository.existsById(UUID.fromString(id))){
            userRepository.deleteById(UUID.fromString(id));
        }else{
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional
    public void addRole(String id, ERole role) {
        User user = userRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new UserNotFoundException());
        user.getRoles().add(role);
    }

    @Override
    @Transactional
    public void removeRole(String id, ERole role) {
        User user = userRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new UserNotFoundException());
        user.getRoles().remove(role);

    }

    @Override
    @Transactional
    public void updatePassword(String id, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new UserNotFoundException());
        if(passwordEncoder.matches(changePasswordRequest.oldPassword(), user.getPassword())){
            user.setPassword(changePasswordRequest.newPassword());
            userRepository.save(user);
        }else{
            throw new PasswordDoesntMatchException();
        }
    }
}
