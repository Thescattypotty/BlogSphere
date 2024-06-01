package org.blogs.blogsphere.Payload.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class PostResponse {
    private String title;
    private byte[] featuredImage;
    private String status;
    private List<CommentResponse> comments = new ArrayList<>();
    private List<CategoryResponse> categories = new ArrayList<>();
    private String createrUsername;
    private Date created_at;
}
