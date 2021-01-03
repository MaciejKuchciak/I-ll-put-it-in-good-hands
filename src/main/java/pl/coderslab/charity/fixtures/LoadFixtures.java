package pl.coderslab.charity.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoadFixtures {

    private CategoryFixture categoryFixture;
    private InstitutionFixture institutionFixture;
    private DonationFixture donationFixture;
    private UserRolesFixture userRolesFixture;
    private UserFixture userFixture;

    @Autowired
    public LoadFixtures(CategoryFixture categoryFixture, InstitutionFixture institutionFixture, DonationFixture donationFixture, UserRolesFixture userRolesFixture, UserFixture userFixture) {
        this.categoryFixture = categoryFixture;
        this.institutionFixture = institutionFixture;
        this.donationFixture = donationFixture;
        this.userRolesFixture = userRolesFixture;
        this.userFixture = userFixture;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        categoryFixture.loadIntoDB();
        institutionFixture.loadIntoDB();
        userRolesFixture.loadIntoDB();
        userFixture.loadIntoDB();
        donationFixture.loadIntoDB();
    }
}
