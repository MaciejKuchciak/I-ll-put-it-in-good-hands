package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Donation;

public interface DonationService {

    void addDonation(Donation donation);

    int sumOfDonations();

    int quantityOfDonations();
}
