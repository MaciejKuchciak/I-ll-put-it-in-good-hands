package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository <Donation, Long>{

    @Query(value = "SELECT SUM(quantity) FROM donations", nativeQuery = true)
    int totalQuantity();

    @Query(value = "SELECT COUNT(DISTINCT id) FROM donations", nativeQuery = true)
    int countAll();

    @Query("Select distinct d from Donation d join d.user u join d.categories c where u.id = ?1 order by d.isReceived desc, d.receiveDate, d.creationDate")
    List<Donation> findAllByUserId(Long id);

}
