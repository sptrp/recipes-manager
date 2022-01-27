package recipes.main.presentationLayer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.main.businessLayer.model.User;
import recipes.main.businessLayer.service.UserService;

import javax.validation.Valid;

@RestController
public class RegistrationRestController {

    @Autowired
    UserService userService;

    @PostMapping("/api/register")
    public void registerUser(@RequestBody @Valid User userInfo) {
        System.out.println("REGISTERING " + SecurityContextHolder.getContext().getAuthentication());
        userService.registerUser(userInfo);
    }
}
