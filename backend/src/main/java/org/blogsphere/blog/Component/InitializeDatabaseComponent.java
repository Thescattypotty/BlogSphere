package org.blogsphere.blog.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.blogsphere.blog.Entity.Post;
import org.blogsphere.blog.Entity.Tag;
import org.blogsphere.blog.Entity.User;
import org.blogsphere.blog.EntityRepository.PostRepository;
import org.blogsphere.blog.EntityRepository.TagRepository;
import org.blogsphere.blog.EntityRepository.UserRepository;
import org.blogsphere.blog.Enum.ERole;
import org.blogsphere.blog.Payload.Request.RegisterRequest;
import org.blogsphere.blog.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitializeDatabaseComponent implements CommandLineRunner {

    private final UserService userService;
    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        createUsers();
        createTags();
        createPosts();
    }

    @Transactional
    public void createUsers() {
        IntStream.range(0, 10).forEach(i -> userService.createUser(
            new RegisterRequest(
                "admin" + i,
                "admin" + i + "@gmail.com",
                "admin" + i,
                "admin",
                "admin",
                Set.of(ERole.ROLE_USER, ERole.ROLE_ADMIN),
                "https://bennis-yahya.vercel.app/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fprofile.873f1cc2.jpeg&w=3840&q=75",
                "admin" + i
            )
        ));
    }

    @Transactional
    public void createTags() {
        List<String> tagNames = List.of(
            "Technology", "Programming", "Java", 
            "Spring Boot", "Database", "Web Development", 
            "Machine Learning", "AI", "Cloud Computing"
        );
        tagNames.forEach(tagName -> 
            {
                Tag tag = tagRepository.saveAndFlush(
                    Tag.builder()
                        .name(tagName)
                        .description(tagName + " Tag Description")
                        .createdBy("admin0")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build());
                System.out.println(tag.getId());
            }
        );
    }

    @Transactional
    public void createPosts() {
        Set<Tag> allTags = Set.copyOf(tagRepository.findAll());
        for(int i = 0 ; i < 10 ; i++){
            postRepository.save(
                Post.builder()
                    .title("Post Title " + i)
                            .content("<div class='blog-content'>" +
                                    "<h1>Article #" + i + "</h1>" +
                                    "<p><i>Introduction au sujet passionnant...</i></p>" +
                                    "<img src='https://bennis-yahya.vercel.app/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Flogo-light.200d1746.png&w=256&q=75' alt='Image descriptive' class='img-fluid'/>" +
                                    "<p>Lorem ipsum dolor sit amet, <b>consectetur adipiscing elit</b>. Sed do eiusmod tempor incididunt ut labore.</p>"
                                    +
                                    "<table class='table table-bordered'>" +
                                    "<tr><th>Colonne 1</th><th>Colonne 2</th></tr>" +
                                    "<tr><td>Donnée 1</td><td>Donnée 2</td></tr>" +
                                    "<tr><td>Donnée 3</td><td>Donnée 4</td></tr>" +
                                    "</table>" +
                                    "<ul>" +
                                    "<li>Point important 1</li>" +
                                    "<li>Point important 2</li>" +
                                    "<li>Point important 3</li>" +
                                    "</ul>" +
                                    "<blockquote>Une citation inspirante pour conclure.</blockquote>" +
                                    "</div>")
                    .coverImage("https://bennis-yahya.vercel.app/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Flogo-light.200d1746.png&w=256&q=75")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .publishedAt(LocalDateTime.now())
                    .isPublished(true)
                    .tags(allTags.stream()
                        .limit(3)
                        .collect(Collectors.toSet()))
                    .createdBy("admin0")
                    //.comments(Set.of())
                    .build()
            );
        }
    }
}
