package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserRolesService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("")
public class UserProfileController {

    private final UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("myprofile")
    public String update(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("user",user);
        model.addAttribute("username",user.getUsername());
        model.addAttribute("userFirstName",user.getFirstName());
        return "my-profile";
    }

    @PostMapping("myprofile")
    public String updateDone(User userInput){
        User user = userService.getByEmail(SecurityUtils.username());
        userService.edit(user, userInput);
        return "redirect:/login";
    }
}
