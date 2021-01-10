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
@RequestMapping("admin")
public class AdminProfileController {

    private final UserService userService;
    private final UserRolesService userRolesService;

    @Autowired
    public AdminProfileController(UserService userService, UserRolesService userRolesService) {
        this.userService = userService;
        this.userRolesService = userRolesService;
    }

    @GetMapping("myprofile")
    public String update(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("user",user);
        model.addAttribute("username",user.getUsername());
        model.addAttribute("userFirstName",user.getFirstName());
        return "admin/my-profile";
    }

    @PostMapping("myprofile")
    public String updateDone(User user){
        user.setUserRoles(userRolesService.getAllUserRoles().get(1));
        user.setLast_update(LocalDateTime.now());
        user.setCreated(user.getCreated());
        user.setActive(true);
        userService.add(user);
        return "redirect:/login";
    }
}
