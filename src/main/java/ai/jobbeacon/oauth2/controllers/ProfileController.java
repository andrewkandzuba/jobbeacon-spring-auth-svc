package ai.jobbeacon.oauth2.controllers;

import ai.jobbeacon.oauth2.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userName}")
    public String getUser(@PathVariable("userName") String userName, Model model) {
        // Retrieve user from a database using userId
        UserDetails userDetails = userService.findByUsername(userName);

        // Add user to model
        model.addAttribute("username", userDetails.getUsername());

        return "profile";
    }
}
