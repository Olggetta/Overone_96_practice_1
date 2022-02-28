package validation;

import encoder.PasswordEncoder;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.UserService;

@Component
public class AuthorizeValidation {

    @Autowired
    private UserService userService;

    public String validateLoginData(String username, String password) {
        String error = "";
        User user = userService.getUserByUsername(username);

        if (user == null) {
            error = "User not found";
        } else if (!user.getPassword().equals(PasswordEncoder.encodePassword(password))) {
            error = "Incorrect password";
        }
        return error;
    }

    public String validateRegistrationData(
            String username,
            String password,
            String repassword
    ) {
        String error = "";

        if (userService.getUserByUsername(username) != null) {
            error = "User already exists";
        } else if (!password.equals(repassword)) {
            error = "Password mismatch";
        }

        return error;
    }
}

