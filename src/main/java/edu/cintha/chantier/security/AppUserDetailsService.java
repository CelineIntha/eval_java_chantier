package edu.cintha.chantier.security;


import edu.cintha.chantier.dao.UserDao;
import edu.cintha.chantier.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDao.findByUsername(username);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Username introuvable");
        }

        return new AppUserDetails(optionalUser.get());
    }
}

