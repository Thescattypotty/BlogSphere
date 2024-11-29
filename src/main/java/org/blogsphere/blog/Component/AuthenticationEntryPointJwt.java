package org.blogsphere.blog.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointJwt implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String,Object> body = new HashMap<>();
        body.put("status",HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error","Unauthorized");
        body.put("message",authException.getMessage());
        body.put("path",request.getServletPath());

        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(response.getOutputStream(),body);
    }
    
}
