package org.blogs.blogsphere.Payload.Request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private byte[] featuredImage;
    private String status;
    private List<Long> category_id;
}
