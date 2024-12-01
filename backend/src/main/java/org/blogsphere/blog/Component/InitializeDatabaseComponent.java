package org.blogsphere.blog.Component;

import java.util.Set;

import org.blogsphere.blog.Enum.ERole;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitializeDatabaseComponent implements CommandLineRunner{
    
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        
        for(int i = 0 ; i < 10 ; i++){
            userService.createUser(
                new RegisterRequest(
                    "admin" + i,
                    "admin" + i + "@gmail.com",
                    "admin" + i,
                    "admin",
                    "admin",
                    Set.of(ERole.ROLE_USER),
                    "admin" + i,
                    "admin" + i
                )
            );
        }
    }
    
}
