package smweb.chillana.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smweb.chillana.model.UserModel;
import smweb.chillana.model.UserProfileModel;
import smweb.chillana.repository.UserProfileRepository;
import smweb.chillana.repository.UserRepository;

import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private UserRepository userRepository;
    private final UserService userService;


    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, UserService userService, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public UserProfileModel getUserProfile(UserModel userModel) {
        UserModel user = userRepository.findByUsername(userModel.getUsername());
        if(user != null) {
            Optional<UserProfileModel> userProfile = userProfileRepository.findByUser(userModel);
            System.out.println("userprofile is present");
            return userProfile.orElse(null);
        }else {
            System.out.println("userprofile not found");
            return null;
        }
    }

    public void UpdateUserProfile(UserProfileModel userProfileModel, String bio,String location, byte[] profileImage,
                                  byte[] backgroundImage) {
        Optional<UserProfileModel> exsitingProfile  = userProfileRepository.findById(userProfileModel.getProfileId());
        if(exsitingProfile.isPresent()) {
            UserProfileModel userProfile = exsitingProfile.get();
            userProfile.setBio(bio);
            userProfile.setLocation(location);
            userProfile.setProfileImage(profileImage);
            userProfile.setBackgroundImage(backgroundImage);
            System.out.println("userprofile updated");
            userProfileRepository.save(userProfile);
        }else {
            System.out.println("userprofile cannot be updated");
        }

    }


    public void saveUserProfile(UserProfileModel userProfileModel) {
        userProfileRepository.save(userProfileModel);
    }


}
