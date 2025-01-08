package ai.jobbeacon.oauth2.controllers;

import ai.jobbeacon.oauth2.domain.AuthDTO;
import ai.jobbeacon.oauth2.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Validated
public class RegisterController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", AuthDTO.User.getInstance());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") AuthDTO.User user, Model model) {
        // Save the user to the database
        if (userService.userExists(user.username())) {
            model.addAttribute("message", "Username already exists!");
            return "register";
        }

        userService.registerUser(
                new User(
                        user.username(),
                        user.password(),
                        List.of(new SimpleGrantedAuthority("USER"))));

        model.addAttribute("message",
                String.format("User %s registered successfully!", user.username()));
        return "success";
    }
}
