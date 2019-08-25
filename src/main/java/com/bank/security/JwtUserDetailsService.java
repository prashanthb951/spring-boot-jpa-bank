package com.bank.security;

import com.bank.model.UserDAO;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO userDAO = userRepository.findByUserName(username).get();
        if (userDAO == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(userDAO.getUsername(), userDAO.getPassword(),
                new ArrayList<>());
    }

    public UserDAO save(UserDAO userDAO) {
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUsername(userDAO.getUsername());
        newUserDAO.setPassword(bcryptEncoder.encode(userDAO.getPassword()));
        return userRepository.save(newUserDAO);
    }


}
