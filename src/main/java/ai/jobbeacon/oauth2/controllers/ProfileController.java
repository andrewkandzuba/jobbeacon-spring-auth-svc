package ai.jobbeacon.oauth2.controllers;

import ai.jobbeacon.oauth2.domain.User;
import ai.jobbeacon.oauth2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public String getUser(@PathVariable("userName") String userName, Model model) {
        // Retrieve user from database using userId
        User user = userService.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException(STR."Invalid user name:\{userName}"));

        // Add user to model
        model.addAttribute("username", user.getUsername());

        return "profile";
    }
}
