package ai.jobbeacon.oauth2.controllers;

import ai.jobbeacon.oauth2.domain.AuthDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("user", AuthDTO.User.getInstance());
        return "login";
    }
}
