package vn.hoidanit.laptopshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService thService) {
        userService = thService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = userService.handleHello();
        model.addAttribute("nam", test);
        model.addAttribute("hoidanit", "spring mvc");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String toSignUser() {
        return "admin/user/create";
    }
}


