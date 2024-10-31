package smweb.chillana.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import smweb.chillana.Service.UserService;
import smweb.chillana.model.UserModel;
import smweb.chillana.repository.UserRepository;



@Controller
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }



    @GetMapping("/index")
    public String publicPage() {
        return "index";
    }

    @GetMapping("/register")
    public String Signup() {
        return "register";
    }
    @GetMapping("login")
    public String LoginPage() {
        return "login";
    }

    @PostMapping("/register")
    public String signup(UserModel userModel, Model model) {

        try {
            UserModel newUser = userService.registerUser(userModel);
            model.addAttribute("successMessage", "User registered successfully!");
            return "login";

        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession httpSession) {
        try{
            UserModel userModel = userService.loginUser(new UserModel(username, password));
            model.addAttribute("successMessage", "User logged in successfully!");

            System.out.println("User logged in:" + userModel.getUsername());
            httpSession.setAttribute("username", userModel.getUsername());

            return "redirect:/userProfile/"+userModel.getUsername();
        }catch (Exception e){
            System.out.println("User can't login error");
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
    }
    @GetMapping("/homepage")
    public String homepage(HttpSession httpSession, Model model) {
        String username = (String) httpSession.getAttribute("username"); // Retrieve username from session
        System.out.println("Retrieved username: " + username); // This should print the username in the console
        model.addAttribute("username", username); // Add username to model
        return "homepage"; // Return the name of your Thymeleaf template
    }




}
