package ro.msg.learning.shop.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Collection;


@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    private Collection<GrantedAuthority> getGrantedAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthorities;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Customer user = customerRepository.findByUserName(userName);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new User(user.getUserName(), passwordEncoder.encode(user.getPassword()), getGrantedAuthorities());
    }
}
