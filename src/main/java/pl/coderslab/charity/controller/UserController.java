package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;

@Controller
@RequestMapping("")
public class UserController {

    @GetMapping("register")
    public String register(){
        return "register";
    }

    @PostMapping("register")
    public String registerDone(Model model){
        model.addAttribute("user",new User());
        return "redirect:/index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }
}
