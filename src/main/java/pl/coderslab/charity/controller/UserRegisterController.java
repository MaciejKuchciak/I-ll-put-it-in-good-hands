package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.app.SendEmailService;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserRolesService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("")
public class UserRegisterController {

    private final UserService userService;
    private final SendEmailService sendEmailService;

    @Autowired
    public UserRegisterController(UserService userService, SendEmailService sendEmailService) {
        this.userService = userService;
        this.sendEmailService = sendEmailService;
    }

    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String registerDone(User user){
        sendEmailService.sendEmail(user.getEmail(),"Witamy na portalu \"Oddam w dobre ręce\".","Potwierdzenie rejestracji");
        userService.add(user);
        return "redirect:/registerconfirmation";
    }

    @GetMapping("registerconfirmation")
    public String registerConfirmation(){
        return "register-confirmation";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("accessdenied")
    public String noAccess(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName",user.getFirstName());
        return "no-privileges";
    }
}
