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
public class UserFormController {

    private final UserService userService;
    private final UserRolesService userRolesService;

    @Autowired
    public UserFormController(UserService userService, UserRolesService userRolesService) {
        this.userService = userService;
        this.userRolesService = userRolesService;
    }

    @GetMapping("adminslist/add")
    public String addNewAdmin(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName",user.getFirstName());
        model.addAttribute("admin", new User());
        return "admin/new-admin";
    }

    @PostMapping("adminslist/add")
    public String institutionAdditionDone(User user){
        user.setUserRoles(userRolesService.getAllUserRoles().get(1));
        user.setCreated(LocalDateTime.now());
        user.setLast_update(LocalDateTime.now());
        user.setActive(true);
        userService.add(user);
        return "redirect:/admin/adminslist";
    }
}
