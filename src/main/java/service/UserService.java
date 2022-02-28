package service;


import encoder.PasswordEncoder;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private void save(User user) {
        userRepository.save(user);
    }

    public void save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(PasswordEncoder.encodePassword(password));
        save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
