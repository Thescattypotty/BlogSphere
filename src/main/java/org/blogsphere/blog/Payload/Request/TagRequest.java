package org.blogsphere.blog.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TagRequest(

    @NotNull
    @NotBlank
    String name,

    String description
){
    
}
