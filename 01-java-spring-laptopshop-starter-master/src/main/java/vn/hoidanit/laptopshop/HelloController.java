package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World for Spring boot!!!!!!!";
    }
    
    @GetMapping("/user")
    public String userPage() {
        return "Only user can access this page";
    }

    @GetMapping("/admin")
    public String password() {
        return "Only admin can access this page";
    }
    
}