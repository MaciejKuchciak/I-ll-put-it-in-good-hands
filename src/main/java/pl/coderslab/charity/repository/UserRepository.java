package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    boolean existsByUsername(String username);

    User getByUsername(String username);

    boolean existsByEmail(String email);

    @Query("Select u from User u where u.email = ?1")
    User getByEmail(String email);

}
