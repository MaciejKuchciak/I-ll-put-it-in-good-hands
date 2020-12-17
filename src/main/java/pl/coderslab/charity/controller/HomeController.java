package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.DonationService;


@Controller
@RequestMapping("")
public class HomeController {

    private final DonationService donationService;

    @Autowired
    public HomeController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/")
    public String homeAction(Model model){
        int sumOfDonations = donationService.sumOfDonations();
        int quantityOfDonations = donationService.quantityOfDonations();
        model.addAttribute("sumOfDonations",sumOfDonations);
        model.addAttribute("quantityOfDonations",quantityOfDonations);
        return "index";
    }
}
