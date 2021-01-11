package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Institution;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution,Long> {

    List<Institution> findAll();

    @Query("Select i from Institution i where i.id = ?1")
    Institution getById(Long id);

}
