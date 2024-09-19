package exercise.controller;

import exercise.Application;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class WelcomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "")
    public String welcome() {
        userRepository.save(Application.getDefaultUser());
        return "Welcome to Spring!";
    }
}
