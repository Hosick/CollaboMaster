package cm.service;

import cm.domain.User;
import cm.model.UserRegistrationModel;
import cm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id).get();
    }

    public User createEntity(UserRegistrationModel userModel){
        User user = new User();
        user.setId(userModel.getId());
        user.setName(userModel.getName());
        user.setPassword(userModel.getPassword1());
        user.setEmail(userModel.getEmail());
        user.setUserType("user");
        return user;
    }

    public void save(UserRegistrationModel userModel){
        User user = createEntity(userModel);
        userRepository.save(user);
    }
}
