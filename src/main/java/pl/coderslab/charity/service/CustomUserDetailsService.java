package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import pl.coderslab.charity.repository.UserRepository;

@RequiredArgsConstructor
@Slf4j
@Service("customUserService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("Searching for user by email '{}'", username);

        if (!userRepository.existsByEmail(username)) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
        final pl.coderslab.charity.entity.User customer = userRepository.getByEmail(username);
        UserDetails user = User.withUsername(customer.getEmail()).password(customer.getPassword()).authorities(customer.getUserRoles().getRole()).build();
        return user;

    }

}
