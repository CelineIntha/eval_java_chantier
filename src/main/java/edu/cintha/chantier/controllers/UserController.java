package edu.cintha.chantier.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.dao.UserDao;
import edu.cintha.chantier.dao.UserDao;
import edu.cintha.chantier.models.User;
import edu.cintha.chantier.models.User;
import edu.cintha.chantier.security.JwtUtils;
import edu.cintha.chantier.views.UserView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/sign-in")
    public void signIn(@RequestBody User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userDao.save(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        try {

            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
            )).getPrincipal();

            return jwtUtils.generateJwt(userDetails);

        } catch (Exception ex) {
            return null;
        }

    }


    @GetMapping("/user/list")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(UserView.class)
    public List<User> list() {
        List<User> userList = userDao.findAll(); // tab ArrayList & List = classe

        return userList;
    }

    @GetMapping("/user/{id}")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(UserView.class)
    public ResponseEntity<User> get(@PathVariable int id) {
        Optional<User> optionalUser = userDao.findById(id);

        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{id}")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(UserView.class)
    public ResponseEntity<User> delete(@PathVariable int id) {
        Optional<User> optionalUser = userDao.findById(id);

        if (optionalUser.isPresent()) {
            userDao.deleteById(id);
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(UserView.class)
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        user.setId(null);
        userDao.save(user);

        Optional<User> optionalUser = userDao.findById(user.getId());
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/user/{id}")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(UserView.class)
    public ResponseEntity<User> update(@RequestBody @Valid User user, @PathVariable int id) {
        user.setId(id);

        Optional<User> optionalUser = userDao.findById(id);

        if (optionalUser.isPresent()) {
            userDao.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/user-by-name/{name}")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(UserView.class)
    public ResponseEntity<User> getByName(@PathVariable String name) {
        Optional<User> optionalUser = userDao.findByUsername(name);

        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
