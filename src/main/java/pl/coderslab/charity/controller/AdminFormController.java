package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserRolesService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("admin")
public class AdminFormController {

    private final UserService userService;
    private final UserRolesService userRolesService;

    @Autowired
    public AdminFormController(UserService userService, UserRolesService userRolesService) {
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
    public String adminAdditionDone(User user){
        userService.addAsAdmin(user);
        return "redirect:/admin/adminslist";
    }

    @GetMapping("adminslist/edit")
    public String editAdmin(Model model, Long id){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName",user.getFirstName());
        User admin = userService.getById(id);
        model.addAttribute("admin",admin);
        return "admin/edit-admin";
    }

    @PostMapping("adminslist/edit")
    public String adminEditDone(User userInput){
        User user = userService.getById(userInput.getId());
        userService.edit(user,userInput);
        return "redirect:/admin/adminslist";
    }

}
