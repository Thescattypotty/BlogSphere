package org.blogs.blogsphere.Payload.Filters;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentFilter {
    Optional<String> status;
    Optional<String> username;
    Optional<Long> postId;
}
