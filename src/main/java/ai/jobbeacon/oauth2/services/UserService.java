package ai.jobbeacon.oauth2.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDetailsManager userDetailsManager,
                       PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDetails userDetails) {
        userDetailsManager.createUser(
                new User(
                        userDetails.getUsername(),
                        passwordEncoder.encode(userDetails.getPassword()),
                        userDetails.getAuthorities()));
    }

    public UserDetails findByUsername(String username) {
        //@TODO: Do not fetch password!
        return userDetailsManager.loadUserByUsername(username);
    }

    public boolean userExists(String username) {
        return userDetailsManager.userExists(username);
    }
}
