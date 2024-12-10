package org.blogsphere.blog.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.blogsphere.blog.Entity.Tag;
import org.blogsphere.blog.Entity.User;
import org.blogsphere.blog.EntityRepository.TagRepository;
import org.blogsphere.blog.EntityRepository.UserRepository;
import org.blogsphere.blog.Enum.ERole;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Service.PostService;
import org.blogsphere.blog.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitializeDatabaseComponent implements CommandLineRunner{
    
    private final UserService userService;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final PostService postService;

    @Override
    public void run(String... args) throws Exception {
        createUsers();
        createTags();
        //createPosts();
    }
    public void createUsers(){
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

    public void createTags(){
        User user = userRepository.findByUsername("admin0").get();
        List<String> tagNames = Arrays.asList(
            "Technology", "Programming", "Java", 
            "Spring Boot", "Database", "Web Development", 
            "Machine Learning", "AI", "Cloud Computing"
        );
        for(String tagName : tagNames){
            tagRepository.save(
                new Tag(
                    null,
                    tagName,
                    "Description for " + tagName + " Tag",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    user
                )
            );
        }
    }

    public void createPosts(){
        for(int i = 0 ; i < 10 ; i++){
            
        }
    }
    
}
