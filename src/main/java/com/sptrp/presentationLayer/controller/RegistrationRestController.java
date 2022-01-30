package com.sptrp.presentationLayer.controller;

import com.sptrp.businessLayer.model.User;
import com.sptrp.businessLayer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
