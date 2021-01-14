package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService{

    private final DonationRepository donationRepository;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public void addDonation(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public int sumOfDonations() {
        return donationRepository.countAll();
    }

    @Override
    public int quantityOfDonations() {
        return donationRepository.totalQuantity();
    }

    @Override
    public List<Donation> getAllDonationsByUserId(Long id) {
        return donationRepository.findAllByUserId(id);
    }

    @Override
    public void setAsClaimed(Long id) {
        Donation donation = donationRepository.getOne(id);
        donation.setReceived(true);
        donation.setReceiveDate(LocalDateTime.now());
        donationRepository.save(donation);
    }
}
