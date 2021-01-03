package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.UserRoles;
import pl.coderslab.charity.repository.UserRolesRepository;

import java.util.List;

@Service
public class UserRolesServiceImpl implements UserRolesService{

    private final UserRolesRepository userRolesRepository;

    @Autowired
    public UserRolesServiceImpl(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public List<UserRoles> getAllUserRoles() {
        return userRolesRepository.findAll();
    }

    @Override
    public void addUserRole(UserRoles userRoles) {
        userRolesRepository.save(userRoles);
    }
}
