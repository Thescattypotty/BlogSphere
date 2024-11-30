package org.blogsphere.blog.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    
    @GetMapping("/granted")
    public ResponseEntity<String> nonuser() {
        return ResponseEntity.ok("Fuck you");
    }

    @GetMapping("/nogranted")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("Fuck you");
    }
}
