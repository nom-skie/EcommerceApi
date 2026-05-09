package com.ws101.Alastoy_Espano.EcommerceApi.service;

import com.ws101.Alastoy_Espano.EcommerceApi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Loads user data from the database for Spring Security's login process.
 * This is called automatically by Spring Security when a login attempt is made.
 *
 * @author Alastoy, Españo
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Called by Spring Security during login.
     * Finds the user by username or throws an exception if not found.
     *
     * @param username the username submitted in the login form
     * @return the UserDetails object Spring Security uses to verify the password
     * @throws UsernameNotFoundException if no user exists with that username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "User not found eith username: " + username
        ));
    }
}
