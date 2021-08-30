package ru.otus.spring.configure;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.otus.spring.jwt.JwtFilter;

@Log
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/books/**").hasAnyRole("ADMIN", "READER")
                .antMatchers(HttpMethod.POST, "/api/v1/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/books/*/review").hasAnyRole("ADMIN", "READER")
                .antMatchers(HttpMethod.POST, "/api/v1/token").permitAll()
                .antMatchers("/**").denyAll()
                .and()
                .httpBasic().disable()
                .formLogin(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }
}
