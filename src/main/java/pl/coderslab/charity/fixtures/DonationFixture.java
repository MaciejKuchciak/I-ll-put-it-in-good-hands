package pl.coderslab.charity.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DonationFixture {

    private DonationService donationService;
    private InstitutionService institutionService;
    private CategoryService categoryService;
    private UserService userService;

    private List<Donation> donationList = Arrays.asList(
            new Donation(null, 20, new ArrayList<>(), null, null, "500-500-500", "Nyska", "Wrocław", "50-555", LocalDate.now(), LocalTime.now(), "Bardzo ciężkie!"),
            new Donation(null, 30, new ArrayList<>(), null, null, "600-600-600", "Wrocławska", "Warszawa", "60-666", LocalDate.now(), LocalTime.now(), "Prawie jak nowe."),
            new Donation(null, 4, new ArrayList<>(), null, null, "300-300-300", "Sienkiewicza", "Kraków", "70-777", LocalDate.now(), LocalTime.now(), "Brak"),
            new Donation(null, 11, new ArrayList<>(), null, null, "100-100-100", "Mickiewicza", "Poznań", "30-333", LocalDate.now(), LocalTime.now(), "Lekko używane."),
            new Donation(null, 66, new ArrayList<>(), null, null, "200-200-200", "Klonowa", "Otmuchów", "10-111", LocalDate.now(), LocalTime.now(), "Same rupiecie.")
    );

    @Autowired
    public DonationFixture(DonationService donationService, InstitutionService institutionService, CategoryService categoryService, UserService userService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
        this.userService = userService;
    }
    public void loadIntoDB() {

        List<Institution> institutionList = institutionService.getAllInstitutions();
        List<Category> categoryList = categoryService.getAllCategories();
        List<User> userList = userService.getAllRegularUsers();

        Donation donation1 = donationList.get(0);
        Donation donation2 = donationList.get(1);
        Donation donation3 = donationList.get(2);
        Donation donation4 = donationList.get(3);
        Donation donation5 = donationList.get(4);

        donation1.getCategories().add(categoryList.get(0));
        donation1.getCategories().add(categoryList.get(1));
        donation2.getCategories().add(categoryList.get(2));
        donation3.getCategories().add(categoryList.get(4));
        donation3.getCategories().add(categoryList.get(2));
        donation4.getCategories().add(categoryList.get(3));
        donation4.getCategories().add(categoryList.get(4));
        donation5.getCategories().add(categoryList.get(0));
        donation5.getCategories().add(categoryList.get(1));
        donation5.getCategories().add(categoryList.get(2));
        donation5.getCategories().add(categoryList.get(3));

        donation1.setInstitution(institutionList.get(0));
        donation2.setInstitution(institutionList.get(1));
        donation3.setInstitution(institutionList.get(2));
        donation4.setInstitution(institutionList.get(3));
        donation5.setInstitution(institutionList.get(4));

        donation1.setUser(userList.get(0));
        donation2.setUser(userList.get(1));
        donation3.setUser(userList.get(2));
        donation4.setUser(userList.get(1));
        donation5.setUser(userList.get(3));

        donationService.addDonation(donation1);
        donationService.addDonation(donation2);
        donationService.addDonation(donation3);
        donationService.addDonation(donation4);
        donationService.addDonation(donation5);
    }
}
