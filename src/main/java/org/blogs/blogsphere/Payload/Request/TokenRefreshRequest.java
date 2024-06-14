package org.blogs.blogsphere.Payload.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenRefreshRequest {
    
    @NotBlank
    private String refreshToken;
}
