package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;

    public UserService(UserRepository theUserRepository, RoleRepository roleRepository, ProductRepository productRepository) {
        userRepository = theUserRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
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

    public User getUserById(long id) {
        User user = userRepository.findById(id);
        return user;
    }

    public void deleteAUser(long id) {
        userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }
    
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
    
    public long getCountsUser() {
        return this.userRepository.count();
    }

    public long getCountsProduct() {
        return this.productRepository.count();
    }
}
