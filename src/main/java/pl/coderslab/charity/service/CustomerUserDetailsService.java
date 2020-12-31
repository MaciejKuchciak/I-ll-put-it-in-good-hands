package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Searching for user by username '{}", username);
        if(!repository.existsByUsername(username)){
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
        pl.coderslab.charity.entity.User userEntity = repository.getByUsername(username);
        return new User(userEntity.getUsername(),
                userEntity.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
        //        userEntity.getRoles().stream().
        //       map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
