package vn.hoidanit.laptopshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.service.UserService;

// @Controller
// public class UserController {
//     @RequestMapping("/")
//     public String getHomePage() {
//         return "Hello from Controller";
//     }
// }

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService theUserService) {
        userService = theUserService;
    }


    @GetMapping("")
    public String getHomePage() {
        return "Hello from Controller";
    }

    @GetMapping("/s")
    public String getHomePageSerivce() {
        return userService.handleHello();
    }
}
