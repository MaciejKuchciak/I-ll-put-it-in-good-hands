package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.util.List;

@Controller
@RequestMapping("")
public class DonationFormController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final UserService userService;

    @Autowired
    public DonationFormController(DonationService donationService, CategoryService categoryService, InstitutionService institutionService, UserService userService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @GetMapping("/form")
    public String donateAction(Model model) {
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName",user.getFirstName());
        model.addAttribute("donation", new Donation());
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions", institutions);
        return "form";
    }

    @PostMapping("/form")
    public String submitAction(Donation donation){
        donationService.addDonation(donation);
        return "redirect:/form-confirmation";
    }

    @GetMapping("form-confirmation")
    public String confirmAction() {
        return "form-confirmation";
    }



}
