package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;


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
        List<User> users = userService.getAllUser();
        model.addAttribute("users1", users);
        return "admin/user/table-user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(@PathVariable long id ,Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);

        return "admin/user/show";
    }

    
    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }
    //@ModelAttribute dùng để lấy dữ liệu từ form, truyền vào một đối tượng (đã có class định nghĩa sẵn), và Spring tự động gán giá trị cho các thuộc tính thông qua setter của đối tượng đó.
     @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUser( @ModelAttribute("newUser") User thUser) {
        userService.handleSaveUser(thUser);
        return "redirect:/admin/user";
    }


    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
         model.addAttribute("newUser", user);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(@ModelAttribute("newUser") User hoidanit) {
        User currentUser = userService.getUserById(hoidanit.getId());
        if (currentUser != null) {
            currentUser.setFullName(hoidanit.getFullName());
            currentUser.setAddress(hoidanit.getAddress());
            currentUser.setPhone(hoidanit.getPhone());
            userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

}
   



