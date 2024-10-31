package smweb.chillana.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smweb.chillana.Service.UserProfileService;
import smweb.chillana.model.UserModel;
import smweb.chillana.model.UserProfileModel;
import smweb.chillana.repository.UserProfileRepository;
import smweb.chillana.repository.UserRepository;

import java.util.Optional;

@Controller
public class UserProfileController {

    private final UserProfileRepository userProfileRepository;
    private UserRepository userRepository;
    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserRepository userRepository, UserProfileService userProfileService, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileService = userProfileService;
        this.userProfileRepository = userProfileRepository;
    }


    @GetMapping("/userProfile/{username}")
    public String profile(@PathVariable String username, Model model, HttpSession session) {
        String sessionUsername = (String) session.getAttribute("username");
        UserModel userModel = userRepository.findByUsername(username);
        UserProfileModel userProfileModel = userProfileService.getUserProfile(userModel);
        if(userProfileModel == null) {
            userProfileModel = new UserProfileModel();
            userProfileModel.setUser(userModel);
        }
        model.addAttribute("userProfile", userProfileModel);
        model.addAttribute("username", username);
        return "userProfile";
    }

    @GetMapping("/profile/edit/{userId}")
    public String edit(@PathVariable Integer userId, Model model) {
        UserModel userModel = userRepository.findById(userId).orElse(null);
        UserProfileModel userProfile = userProfileService.getUserProfile(userModel);
        if(userProfile == null) {
            userProfile = new UserProfileModel();
            userProfile.setUser(userModel);
            userProfileService.saveUserProfile(userProfile);

        }
        model.addAttribute("userProfile", userProfile);
        model.addAttribute("username", userModel.getUsername());
        return "editProfile";

    }
    @PostMapping("/profile/edit/{userId}")
    public String edit(@ModelAttribute UserProfileModel userProfileModel, @RequestParam String location,
                       @RequestParam String bio, @RequestParam MultipartFile profileImage,
                       @RequestParam MultipartFile backgroundImage,Model model) {
        try {
            byte[] profileImageBytes = profileImage.getBytes();
            byte[] backgroundImageBytes = backgroundImage.getBytes();
            userProfileService.saveUserProfile(userProfileModel);
            model.addAttribute("userProfile", userProfileModel);
            model.addAttribute("succesMessage", "userProfile updated successfully");

        }catch (Exception e) {
            model.addAttribute("errorMessage", "userProfile cannot be updated");
        }
        return "redirect:/userProfile/"+userProfileModel.getUser().getUsername();
    }



}
