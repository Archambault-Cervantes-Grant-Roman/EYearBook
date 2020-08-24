package com.codeup.eyearbook;

import com.codeup.eyearbook.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    /* Login configuration */
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/") // user's home page, it can be any URL
                    .permitAll() // Anyone can go to the login page
                    /* Logout configuration */
                    .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout") // append a query string value
                    /* Pages that can be viewed without having to log in */
                    .and()
                    .authorizeRequests()
                    .antMatchers("/", "/sign-up") // anyone can see the home and the ads pages
                    .permitAll()
                    /* Pages that require authentication */
                    .and()
                    .authorizeRequests()
                    .antMatchers("/yearbook").hasAnyAuthority("OWNSYEARBOOK")
                    .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
                    .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                    .antMatchers("/delete/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()

            ;
    }
}