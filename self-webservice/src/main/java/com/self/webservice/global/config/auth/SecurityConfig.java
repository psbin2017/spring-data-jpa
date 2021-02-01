package com.self.webservice.global.config.auth;

import com.self.webservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// security enable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // used by h2-console
            .csrf().disable()
            .headers().frameOptions().disable()
            // used by h2-console

            // role matcher
            .and()
                .authorizeRequests()
                .antMatchers(",", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole( Role.USER.name() )
                .anyRequest().authenticated()
            // role matcher

            // redirect url
            .and()
                .logout()
                    .logoutSuccessUrl("/")
            // redirect url

            // login service
            .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService( customOAuth2UserService );
            // login service
    }
}
