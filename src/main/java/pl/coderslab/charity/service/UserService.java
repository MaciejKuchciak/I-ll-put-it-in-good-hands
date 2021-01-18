package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void add(User user);

    void addAsAdmin(User user);

    void edit(User userData, User userUpdate);

    User getByEmail(String email);

    User getById(Long id);

    List<User> getAllAdmins();

    List<User> getAllRegularUsers();

    void delete(Long id);

    void setActive(Long id);

    void setInactive(Long id);

}
