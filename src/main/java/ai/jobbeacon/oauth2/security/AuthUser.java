package ai.jobbeacon.oauth2.security;

import ai.jobbeacon.oauth2.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthUser extends User implements UserDetails {
    private final User user;
    public AuthUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getGrantedAuthority()));
    }
}
