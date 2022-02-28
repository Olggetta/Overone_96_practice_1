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
public class RegistrationController {

    private UserService userService;
    private AuthorizeValidation authorizeValidation;

    @Autowired
    public RegistrationController(UserService userService, AuthorizeValidation authorizeValidation) {
        this.userService = userService;
        this.authorizeValidation = authorizeValidation;
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "register_page";
    }

    @PostMapping("/registration")
    public String checkInputData(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("repassword") String repassword,
            Model model
    ) {
        String error = authorizeValidation.validateRegistrationData(username, password, repassword);

        if (!error.isEmpty()) {
            model.addAttribute("error", error);
            return "register_page";
        } else {
            userService.save(username, password);
            return "redirect:/";
        }
    }
}

