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
        //TODO: email and pw to be changed via e-mail, username should not be in the app
//        user.setUsername(user.getUsername());
//        user.setEmail(userInput.getEmail());
        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
//        user.setPassword(userInput.getPassword());
        user.setLast_update(LocalDateTime.now());
        userService.edit(user);
        return "redirect:/admin/userslist";
    }

}
