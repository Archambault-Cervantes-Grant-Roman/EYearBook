package com.codeup.eyearbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.transaction.Transactional;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private com.codeup.eyearbook.services.UserDetailsLoader usersLoader;

    public SecurityConfiguration(com.codeup.eyearbook.services.UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                /* Login configuration */
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/") // user's home page, it can be any URL
//                .permitAll() // Anyone can go to the login page
//                /* Logout configuration */
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout") // append a query string value
//                /* Pages that can be viewed without having to log in */
//                .and()
//                .authorizeRequests()
//                .antMatchers("/") // anyone can see the home and the ads pages
//                .permitAll()
//                /* Pages that require authentication */
//                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        "/users/parent-profile",
//                        "/users/sign-up",
//                        "/users/signature-page",
//                        "/users/edit-profile" ,
//                        "/search"// only authenticated users can edit profile
//
//                )
//                .authenticated()
//        ;
//    }
        http
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("my-remember-me-cookie")
                .permitAll()
                .and()
//                .rememberMe()
//                //.key("my-secure-key")
//                .rememberMeCookieName("my-remember-me-cookie")
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(24 * 60 * 60)
//                .and()
                .exceptionHandling()
        ;
    }

//    PersistentTokenRepository persistentTokenRepository(){
//        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
//        tokenRepositoryImpl.setDataSource(dataSource);
//        return tokenRepositoryImpl;
//    }
}