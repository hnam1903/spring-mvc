package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }


    public String handleHello() {
        return "Hello from Service";
    }
    

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUserByEmailAndAddress(String email, String address) {
        return userRepository.findByEmailAndAddress(email, address);
    }

    public User handleSaveUser(User user) {
        User theUser = userRepository.save(user);
        System.out.println(theUser);
        return theUser;

    }

}
