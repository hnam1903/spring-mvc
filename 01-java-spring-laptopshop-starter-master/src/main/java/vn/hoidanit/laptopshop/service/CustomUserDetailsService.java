package vn.hoidanit.laptopshop.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // User trung voi ten cua class User trong spring the nen la phai khai bao package o duoi 
        vn.hoidanit.laptopshop.domain.User user = this.userService.getUserByEmail(username); //username o day la email,sdt,cccd tuy vao du an nen se de ten la username
        if (username == null) {
            throw new UsernameNotFoundException("user not found");
        }
        // tinh da hinh khi User implements tu UserDetail nen chi can tao lop con(van la 1 loai cua UserDetail)
        return new User(
            user.getEmail(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        
    }
    
}
