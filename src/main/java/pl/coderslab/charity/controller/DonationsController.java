package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;

import java.util.List;

@Controller
@RequestMapping("")
public class DonationsController {

    private final UserService userService;
    private final DonationService donationService;

    @Autowired
    public DonationsController(UserService userService, DonationService donationService) {
        this.userService = userService;
        this.donationService = donationService;
    }

    @GetMapping("donations")
    public String donationList(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName", user.getFirstName());
        List<Donation> donations = donationService.getAllDonationsByUserId(user.getId());
        model.addAttribute("donations",donations);
        return "donations";
    }
}
