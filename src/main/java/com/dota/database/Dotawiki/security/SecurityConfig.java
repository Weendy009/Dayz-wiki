package com.dota.database.Dotawiki.security;


import com.dota.database.Dotawiki.entity.users.UserOauth;
import com.dota.database.Dotawiki.repository.UserOauthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;

import java.time.LocalDateTime;


@EnableJdbcHttpSession
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authorization", "/api/weapons/filter", "/api/armors/filter", "/adviсe_equipment", "/backpacks", "/armors", "/weapons", "/change",
                        "/register", "/authenticate", "/reset", "/singup", "/", "/reset/password/**", "/activeToken/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/")
                .permitAll()
                .and()
                .sessionManagement()
                .maximumSessions(3)
                .maxSessionsPreventsLogin(false)
                .and()
                .sessionAuthenticationErrorUrl("/login")
                .sessionFixation().migrateSession()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserOauthRepository repository) {
        return map -> {
            String idString = (String) map.get("sub");
            UserOauth user = repository.findById(idString).orElseGet(() -> {
                UserOauth newUser = new UserOauth();
                newUser.setId(idString);
                newUser.setName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                return newUser;
            });
            user.setLastActive(LocalDateTime.now());
            return repository.save(user);
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public ConcurrentSessionControlAuthenticationStrategy concurrentSessionStrategy(SessionRegistry sessionRegistry) {
        ConcurrentSessionControlAuthenticationStrategy strategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
        strategy.setMaximumSessions(3);
        strategy.setExceptionIfMaximumExceeded(true);
        return strategy;
    }

}
