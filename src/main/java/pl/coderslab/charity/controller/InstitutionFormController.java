package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

@Controller
@RequestMapping("admin")
public class InstitutionFormController {

    private final InstitutionService institutionService;
    private final UserService userService;

    @Autowired
    public InstitutionFormController(InstitutionService institutionService, UserService userService) {
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @GetMapping("institutions/add")
    public String addInstitution(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName",user.getFirstName());
        model.addAttribute("institution",new Institution());
        return "admin/new-institution";
    }

    @PostMapping("institutions/add")
    public String institutionAdditionDone(Institution institution){
        institutionService.addInstitution(institution);
        return "redirect:/admin/institutions";
    }

    @GetMapping("institutions/edit")
    public String editInstitution(Model model, Long id){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName",user.getFirstName());
        Institution institution = institutionService.getById(id);
        model.addAttribute("institution",institution);
        return "admin/edit-institution";
    }

    @PostMapping("institutions/edit")
    public String institutionEditDone(Institution institution){
        institutionService.addInstitution(institution);
        return "redirect:/admin/institutions";
    }
}
