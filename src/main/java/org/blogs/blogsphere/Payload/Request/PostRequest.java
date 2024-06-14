package org.blogs.blogsphere.Payload.Request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private byte[] featuredImage;
    @NotBlank
    private String status;
    private List<Long> category_id;
}
