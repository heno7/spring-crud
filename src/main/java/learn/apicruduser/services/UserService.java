package learn.apicruduser.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learn.apicruduser.entities.User;
import learn.apicruduser.repositories.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> getUser(long id) {
        return this.userRepository.findById(id);
    }

    public User updateUser(long id, Optional<User> userUpdateData) {
        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent()) {
            User existUser = user.get();
            User userData = userUpdateData.get();
            System.out.println(userData);
            if(userData.getName() != null) {
                existUser.setName(userData.getName());
            } else {
                existUser.setName(existUser.getName());
            }

            if(userData.getEmail() != null) {
                existUser.setEmail(userData.getEmail());
            } else {
                existUser.setEmail(existUser.getEmail());
            }

            if(userData.getPassWord() != null) {
                existUser.setPassWord(userData.getPassWord());
            } else {
                existUser.setPassWord(existUser.getPassWord());
            }
            this.userRepository.save(existUser);
            return existUser;
        }
        return null;
    }

    public Optional<User> deleteUser(long id) {
        Optional<User> user = this.userRepository.findById(id); 
        this.userRepository.deleteById(id);
        return user;
    }

}
