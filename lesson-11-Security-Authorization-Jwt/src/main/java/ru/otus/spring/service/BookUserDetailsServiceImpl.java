package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Account;

@Service
@RequiredArgsConstructor
public class BookUserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = accountService.findByName(userName);
        return User.builder()
                .username(account.getName())
                .password(account.getPassword())
                .authorities(AuthorityUtils.createAuthorityList("ROLE_" + account.getRole()))
                .accountExpired(false)
                .accountLocked(false)
                .build();
    }
}
