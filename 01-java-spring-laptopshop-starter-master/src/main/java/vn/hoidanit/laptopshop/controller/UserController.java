package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
    private final UserService userService;



    public UserController(UserService thService) {
        userService = thService;
      
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUserGetAll = userService.getAllUser();
        System.out.println(arrUserGetAll);

        List<User> arrGetUserByEmail = userService.getAllUsersByEmail("1@gmail.com");
        System.out.println(arrGetUserByEmail);

        List<User> arrGetUserByEmailAndAddress = userService.getAllUserByEmailAndAddress("1@gmail.com", "HCM");
        System.out.println(arrGetUserByEmailAndAddress);
        model.addAttribute("nam", "test");
        model.addAttribute("hoidanit", "spring mvc");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
//@ModelAttribute dùng để lấy dữ liệu từ form, truyền vào một đối tượng (đã có class định nghĩa sẵn), và Spring tự động gán giá trị cho các thuộc tính thông qua setter của đối tượng đó.
     @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST)
    public String createUser( @ModelAttribute("newUser") User thUser) {
        System.out.println(thUser);
        userService.handleSaveUser(thUser);
        return "hello";
    }
}


