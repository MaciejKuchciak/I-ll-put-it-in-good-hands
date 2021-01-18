package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRolesService userRolesService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRolesService userRolesService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRolesService = userRolesService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRoles(userRolesService.getAllUserRoles().get(0));
        user.setCreated(LocalDateTime.now());
        user.setLast_update(LocalDateTime.now());
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public void addAsAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRoles(userRolesService.getAllUserRoles().get(1));
        user.setCreated(LocalDateTime.now());
        user.setLast_update(LocalDateTime.now());
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void edit(User userData, User userUpdate) {
        userData.setLast_update(LocalDateTime.now());
        userData.setUsername(userUpdate.getUsername());
        userData.setFirstName(userUpdate.getFirstName());
        userData.setLastName(userUpdate.getLastName());
        //TODO: email and pw to be changed via e-mail, username should not be in the app
//        user.setEmail(userInput.getEmail());
        if(userUpdate.getPassword() != null)
            userData.setPassword(userUpdate.getPassword());
        userRepository.save(userData);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAllAdmins() {
        return userRepository.findAllByUserRoles("ROLE_ADMIN");
    }

    @Override
    public List<User> getAllRegularUsers() {
        return userRepository.findAllByUserRoles("ROLE_USER");
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void setActive(Long id) {
        User user = userRepository.getOne(id);
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void setInactive(Long id) {
        User user = userRepository.getOne(id);
        user.setActive(false);
        userRepository.save(user);
    }
}
