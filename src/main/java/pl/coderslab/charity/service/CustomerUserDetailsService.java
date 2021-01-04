package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Searching for user by email '{}'", email);

        if (!userRepository.existsByEmail(email)) {
            throw new UsernameNotFoundException(String.format("Username %s not found", email));
        }
        pl.coderslab.charity.entity.User user = userRepository.getByEmail(email);
        return new User(user.getEmail(),
                user.getPassword(),
                getAuthorities(user));
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(pl.coderslab.charity.entity.User user) {
        String[] usersRoles = user.getUserRoles().getUserList().stream().map((userRoles) -> user.getUserRoles().getRole()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(usersRoles);
        return authorities;
    }
}
