package org.blogs.blogsphere.Payload.Filters;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostFilter {
    Optional<String> status;
    Optional<String> username; 
    Optional<Date> createdAt;
    Optional<String> category;
}
