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
import pl.coderslab.charity.service.UserService;

@Controller
@RequestMapping("")
public class ContactController {

    private final UserService userService;
    private final SendEmailService sendEmailService;

    @Autowired
    public ContactController(UserService userService, SendEmailService sendEmailService) {
        this.userService = userService;
        this.sendEmailService = sendEmailService;
    }

    @GetMapping("contactconfirmation")
    public String contactFormConfirmation(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        if(user != null) {
            model.addAttribute("userFirstName", user.getFirstName());
        }
        return "contact-confirmation";
    }

    @PostMapping("contactForm")
    public String contactForm(String message, String firstName, String lastName, String email){
        sendEmailService.sendEmailToMyselfFromContactForm(String.format("Wiadomość: %s,\nImię: %s,\nNazwisko: %s,\nE-mail: %s",message,firstName,lastName,email),email);
        sendEmailService.sendEmail(email,String.format("Witaj %s %s, dziękujemy za Twoją wiadomość. Skontaktujemy się z Tobą tak szybko jak będzie to tylko możliwe.",firstName,lastName),"Potwierdzenie prośby o kontakt");
        return "redirect:/contactconfirmation";
    }


}
