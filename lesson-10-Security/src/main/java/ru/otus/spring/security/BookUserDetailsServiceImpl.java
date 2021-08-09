package ru.otus.spring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Account;
import ru.otus.spring.repositories.AccountRepository;

@Service
@RequiredArgsConstructor
public class BookUserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByName(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
        return User.builder()
                .username(account.getName())
                .password(account.getPassword())
                .authorities(AuthorityUtils.createAuthorityList("ROLE_" + account.getRole()))
                .accountExpired(false)
                .accountLocked(false)
                .build();
    }
}
