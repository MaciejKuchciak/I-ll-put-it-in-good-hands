package pl.coderslab.charity.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DonationFixture {

    private DonationService donationService;
    private InstitutionService institutionService;
    private CategoryService categoryService;
    private List<Donation> donationList = Arrays.asList(
            new Donation(null,20,null,null,"Nyska","Wrocław","50-555", LocalDate.now(), LocalTime.now(),"Bardzo ciężkie!"),
            new Donation(null,30,null,null,"Wrocławska","Warszawa","60-666", LocalDate.now(), LocalTime.now(),"Prawie jak nowe"),
            new Donation(null,40,null,null,"Sienkiewicza","Kraków","70-777", LocalDate.now(), LocalTime.now(),"Brak")
    );

    @Autowired
    public DonationFixture(DonationService donationService, InstitutionService institutionService, CategoryService categoryService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
    }
    public void loadIntoDB() {
        for (Donation donation : donationList){
            donationService.addDonation(donation);
        }
        List<Institution> institutionList = institutionService.getAllInstitutions();
        List<Category> categoryList = categoryService.getAllCategories();
        Donation donation1 = donationList.get(0);
        Donation donation2 = donationList.get(1);
        donation1.setCategory(categoryList.get(0));
        donation2.setCategory(categoryList.get(1));
        donation1.setInstitution(institutionList.get(0));
        donation2.setInstitution(institutionList.get(0));
        donationService.addDonation(donation1);
        donationService.addDonation(donation2);
    }
}
