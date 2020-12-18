package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.DonationService;

@Controller
@RequestMapping("")
public class DonationController {

    private final DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/form")
    public String donateAction(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/form")
    public String submitAction(Donation donation){
        donationService.addDonation(donation);
        return "redirect:/form-confirmation";
    }

    @GetMapping("form-confirmation")
    public String confirmAction(Model model) {
        return "form-confirmation";
    }



}
