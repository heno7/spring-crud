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

    public User updateUser(long id, User userUpdateData) {
        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent()) {
            User existUser = user.get();
            System.out.println(userUpdateData);
            if(userUpdateData.getName() != null) {
                existUser.setName(userUpdateData.getName());
            } else {
                existUser.setName(existUser.getName());
            }

            if(userUpdateData.getEmail() != null) {
                existUser.setEmail(userUpdateData.getEmail());
            } else {
                existUser.setEmail(existUser.getEmail());
            }

            if(userUpdateData.getPassWord() != null) {
                existUser.setPassWord(userUpdateData.getPassWord());
            } else {
                existUser.setPassWord(existUser.getPassWord());
            }
            this.userRepository.save(existUser);
            return existUser;
        }
        return null;
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getUserByName(String name) {
      return this.userRepository.findByName(name);
    }

}
