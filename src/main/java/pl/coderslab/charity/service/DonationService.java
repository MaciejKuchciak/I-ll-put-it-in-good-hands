package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Donation;

import java.util.List;

public interface DonationService {

    void addDonation(Donation donation);

    int sumOfDonations();

    int quantityOfDonations();

    List<Donation> getAllDonationsByUserId(Long id);

    void setAsClaimed(Long id);

    Donation getById(Long id);
}
