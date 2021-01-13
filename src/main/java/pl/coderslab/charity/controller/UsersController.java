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
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("userslist")
    public String userList(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName", user.getFirstName());
        List<User> users = userService.getAllRegularUsers();
        model.addAttribute("users",users);
        return "admin/users";
    }

    @GetMapping("userslist/delete")
    public String deleteUser(Long id){
        userService.delete(id);
        return "redirect:/admin/userslist";
    }

    @GetMapping("userslist/setactive")
    public String activateUser(Long id){
        userService.setActive(id);
        return "redirect:/admin/userslist";
    }

    @GetMapping("userslist/setinactive")
    public String deactivateUser(Long id){
        userService.setInactive(id);
        return "redirect:/admin/userslist";
    }
}
