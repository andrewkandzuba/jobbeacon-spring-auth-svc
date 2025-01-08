package ai.jobbeacon.oauth2.controllers;

import ai.jobbeacon.oauth2.domain.User;
import ai.jobbeacon.oauth2.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Validated
public class RegisterController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        // Save the user to the database
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("message", "Username already exists!");
            return "register";
        }
        userService.registerUser(user);

        model.addAttribute("message",
                String.format("User %s registered successfully!", user.getUsername()));
        return "success";
    }
}
