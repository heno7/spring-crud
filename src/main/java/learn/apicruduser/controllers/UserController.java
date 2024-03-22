package learn.apicruduser.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import learn.apicruduser.DTOs.UserDeleteDTO;
import learn.apicruduser.entities.User;
import learn.apicruduser.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return this.userService.createUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUser(@PathVariable long id) {
        return this.userService.getUser(id);
    }

    @PutMapping(path="/{id}")
    public User updatUser(@PathVariable long id, @Valid @RequestBody Optional<User> userUpdateData) {
        return this.userService.updateUser(id, userUpdateData);
    }

    @DeleteMapping(path = "/{id}")
    public UserDeleteDTO<Optional<User>> deleteUser(@PathVariable long id) {
        Optional<User> userDeleted= this.userService.deleteUser(id);
        return new UserDeleteDTO<Optional<User>>("Deleted", userDeleted);
    }
}
