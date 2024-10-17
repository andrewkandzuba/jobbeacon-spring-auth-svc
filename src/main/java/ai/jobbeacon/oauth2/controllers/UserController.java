package ai.jobbeacon.oauth2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/{username}")
    public String hello(@PathVariable("username") String id) {
        return String.format("Hello, User %s!", id);
    }
}
