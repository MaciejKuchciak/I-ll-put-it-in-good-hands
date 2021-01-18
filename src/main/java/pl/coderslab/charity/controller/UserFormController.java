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

    @Autowired
    public UserFormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("userslist/edit")
    public String editUser(Model model, Long id){
        User loggedAdmin = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName",loggedAdmin.getFirstName());
        User user = userService.getById(id);
        model.addAttribute("user",user);
        return "admin/edit-user";
    }

    @PostMapping("userslist/edit")
    public String userEditDone(User userInput){
        User user = userService.getById(userInput.getId());
        userService.edit(user,userInput);
        return "redirect:/admin/userslist";
    }

}
