package edu.cintha.chantier.security;

import edu.cintha.chantier.models.User;
import edu.cintha.chantier.models.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {

    private User user;

    public AppUserDetails(User user) {
        this.user = user;
    }
//    public AppUserDetails(UserRole userRole) {this.userRole = userRole;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (user.getRole().getId() == 3) {
            return List.of(new SimpleGrantedAuthority("ROLE_OUVRIER"));
        } else if ((user.getRole().getId() == 1)) {
            return List.of(new SimpleGrantedAuthority("ROLE_CHEFDECHANTIER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
        }

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
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

