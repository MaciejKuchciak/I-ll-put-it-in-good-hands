package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminsController {

    private final UserService userService;

    @Autowired
    public AdminsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("adminslist")
    public String adminList(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName", user.getFirstName());
        model.addAttribute("loggedUserId", user.getId());
        List<User> admins = userService.getAllAdmins();
        model.addAttribute("admins",admins);
        return "admin/admins";
    }

    @GetMapping("adminslist/delete")
    public String deleteAdmin(Long id){
        User user = userService.getByEmail(SecurityUtils.username());
        if(user.getId() != id){
            userService.delete(id);
        }
        return "redirect:/admin/adminslist";
    }
}
