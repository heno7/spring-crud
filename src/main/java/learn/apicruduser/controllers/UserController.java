package learn.apicruduser.controllers;


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

import learn.apicruduser.dtos.UserCreateDto;
import learn.apicruduser.dtos.UserResponseDto;
import learn.apicruduser.dtos.UserUpdateDto;
import learn.apicruduser.entities.User;
import learn.apicruduser.exceptions.UserExistException;
import learn.apicruduser.exceptions.UserNotFoundException;
import learn.apicruduser.services.UserMapper;
import learn.apicruduser.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResponseDto<User> createUser(@Valid @RequestBody UserCreateDto userDto) {
        var existUserEmail = userService.getUserByEmail(userDto.getEmail());
        if(existUserEmail != null) {
            throw new UserExistException("user with email is already exist");
        }
        var existUserName = userService.getUserByName(userDto.getName());
        if(existUserName != null) {
            throw new UserExistException("user with name is already exist");
        }
        var user = userMapper.toUserCreate(userDto);
        var userCreated =  this.userService.createUser(user);
        return new UserResponseDto<User>(HttpStatus.CREATED.value(), "Created", userCreated);
    }

    @GetMapping(path = "/{id}")
    public UserResponseDto<User> getUser(@PathVariable long id) {
        User user = this.userService.getUser(id).orElseThrow(() -> new UserNotFoundException("user with the given id not found"));
        return new UserResponseDto<User>(HttpStatus.OK.value(), "Ok", user);
    }

    @PutMapping(path="/{id}")
    public UserResponseDto<User> updatUser(@PathVariable long id, @Valid @RequestBody UserUpdateDto dto) {
        this.userService.getUser(id).orElseThrow(() -> new UserNotFoundException("user with the given id not found"));
        var existUserName = userService.getUserByName(dto.getName());
        if(existUserName != null) {
            throw new UserExistException("user with name is already exist");
        }
        User userUpdate = userMapper.toUpdateUser(dto);
        User user = this.userService.updateUser(id, userUpdate);
        return new UserResponseDto<User>(HttpStatus.OK.value(), "Ok", user);
    }

    @DeleteMapping(path = "/{id}")
    public UserResponseDto<User> deleteUser(@PathVariable long id) {
        User userDeleted = this.userService.getUser(id).orElseThrow(() -> new UserNotFoundException("user with the given id not found"));
        this.userService.deleteUser(id);
        return new UserResponseDto<User>(HttpStatus.OK.value(), "Deleted", userDeleted);
    }
}
