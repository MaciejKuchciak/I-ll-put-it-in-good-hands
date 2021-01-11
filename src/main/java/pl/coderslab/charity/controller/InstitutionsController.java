package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.app.SecurityUtils;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.util.List;

@Controller
@RequestMapping("admin")
public class InstitutionsController {

    private final InstitutionService institutionService;
    private final UserService userService;

    @Autowired
    public InstitutionsController(InstitutionService institutionService, UserService userService) {
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @GetMapping("institutions")
    public String institutionList(Model model){
        User user = userService.getByEmail(SecurityUtils.username());
        model.addAttribute("userFirstName", user.getFirstName());
        List<Institution> institutions = institutionService.getAllInstitutions();
        model.addAttribute("institutions",institutions);
        return "admin/institutions";
    }

    @GetMapping("institutions/delete")
    public String deleteInstitution(Long id){
        institutionService.delete(id);
        return "redirect:/admin/institutions";
    }
}
