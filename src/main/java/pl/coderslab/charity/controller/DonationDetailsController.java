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

@Controller
@RequestMapping("")
public class DonationDetailsController {

    private final DonationService donationService;
    private final UserService userService;

    @Autowired
    public DonationDetailsController(DonationService donationService, UserService userService) {
        this.donationService = donationService;
        this.userService = userService;
    }

    @GetMapping("donations/details")
    public String showDonationDetails(Model model, Long id) {
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName",user.getFirstName());
        Donation donation = donationService.getById(id);
        model.addAttribute("donation",donation);
        return "donation-details";
    }
}
