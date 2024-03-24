package learn.apicruduser.services;

import org.springframework.stereotype.Service;

import learn.apicruduser.dtos.UserCreateDto;
import learn.apicruduser.dtos.UserUpdateDto;
import learn.apicruduser.entities.User;

@Service
public class UserMapper {
    public User toUserCreate(UserCreateDto dto) {
        var user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassWord(dto.getPassWord());
        return user;
    }

    public User toUpdateUser(UserUpdateDto dto) {
        var user = new User();
        user.setName(dto.getName());
        user.setPassWord(dto.getPassWord());
        return user;
    }
}
