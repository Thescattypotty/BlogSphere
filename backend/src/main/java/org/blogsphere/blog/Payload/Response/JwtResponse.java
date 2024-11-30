package org.blogsphere.blog.Payload.Response;

public record JwtResponse(
    String type,
    String accessToken,
    String refreshToken
) {

}
