package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.UserRoles;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long>{

    List<UserRoles> findAll();

}
