package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;
import validation.AuthorizeValidation;

@Controller
public class LoginController {

    private UserService userService;
    private AuthorizeValidation authorizeValidation;

    @Autowired
    public LoginController(UserService userService, AuthorizeValidation authorizeValidation) {
        this.userService = userService;
        this.authorizeValidation = authorizeValidation;
    }


    @GetMapping
    public String showLoginPage() {
    return "login_page";
}

    @PostMapping("/login")
    public String checkInputData(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ){{
        String error = authorizeValidation.validateLoginData(username, password);

        if (!error.isEmpty()) {
            model.addAttribute("error", error);
            return "login_page";
        } else {
            return "redirect:/home";
        }
    }

    }

    }

