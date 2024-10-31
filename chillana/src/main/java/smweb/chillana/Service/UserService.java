package smweb.chillana.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smweb.chillana.model.UserModel;
import smweb.chillana.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(UserModel userModel) {
        if (userRepository.findByUsername(userModel.getUsername()) == null) {
            UserModel newUser = new UserModel();
            System.out.println("User have been registered");
            return userRepository.save(userModel);
        }else {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    public UserModel loginUser(UserModel userModel) {
        UserModel UserExists = userRepository.findByUsername(userModel.getUsername());
        if (UserExists != null || UserExists.getPassword().equals(userModel.getPassword())) {
            System.out.println("User logged in");
            return UserExists;
        }else {
            throw new IllegalArgumentException("Username or password doesn't match");
        }


    }
}
