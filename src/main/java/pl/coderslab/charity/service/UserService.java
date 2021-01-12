package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void add(User user);

    void edit(User user);

    User getByEmail(String email);

    List<User> getAllAdmins();

    List<User> getAllRegularUsers();

    void delete(Long id);

}
