package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.entity.User;
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
        model.addAttribute("userFirstName",user.getFirstName());
        return "myprofile";
    }

    @PostMapping("myprofile")
    public String updateDone(User user){
        user.setLast_update(LocalDateTime.now());
        userService.add(user);
        return "myprofile";
    }
}
