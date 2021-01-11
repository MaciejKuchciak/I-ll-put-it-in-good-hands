package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.util.ArrayList;

@Controller
@RequestMapping("admin")
public class AdminHomeController {

    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final UserService userService;

    @Autowired
    public AdminHomeController(DonationService donationService, InstitutionService institutionService, UserService userService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @GetMapping("")
    public String adminHomeAction(Model model){
        ArrayList<ArrayList<Institution>> listOfLists = institutionService.getListOfInstsLists();
        model.addAttribute("institutions",listOfLists);
        int sumOfDonations = donationService.sumOfDonations();
        int quantityOfDonations = donationService.quantityOfDonations();
        model.addAttribute("sumOfDonations",sumOfDonations);
        model.addAttribute("quantityOfDonations",quantityOfDonations);
        User user = userService.getByEmail(SecurityUtils.username());
        if(user != null) {
            model.addAttribute("userFirstName", user.getFirstName());
        }
        return "admin/index";
    }
}
