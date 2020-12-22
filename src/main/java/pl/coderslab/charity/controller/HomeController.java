package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.ArrayList;

@Controller
@RequestMapping("")
public class HomeController {

    private final DonationService donationService;
    private final InstitutionService institutionService;

    @Autowired
    public HomeController(DonationService donationService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    @GetMapping("/")
    public String homeAction(Model model){
        ArrayList<ArrayList<Institution>> listOfLists = institutionService.getListOfInstsLists();

        model.addAttribute("institutions",listOfLists);
        int sumOfDonations = donationService.sumOfDonations();
        int quantityOfDonations = donationService.quantityOfDonations();
        model.addAttribute("sumOfDonations",sumOfDonations);
        model.addAttribute("quantityOfDonations",quantityOfDonations);
        return "index";
    }
}
