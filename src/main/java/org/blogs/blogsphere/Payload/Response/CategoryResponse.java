package org.blogs.blogsphere.Payload.Response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String createrUsername;
    private Date created_at;
}
