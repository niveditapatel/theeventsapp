package com.eventmanagement.eventmanagement.configuration;


import com.eventmanagement.eventmanagement.repository.UserRepository;
import com.eventmanagement.eventmanagement.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configurable
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bcryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);

        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    // BCrypt Encryption for password protection and user security
    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // No Encryption for password for Testing
    private PasswordEncoder simplePasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                .anyRequest().permitAll();
                .antMatchers("/public/**").permitAll()
                .antMatchers("/api/getUser").hasRole("ADMIN")
                .antMatchers("/api/deleteEvent/**").hasAnyRole("ADMIN","CREATOR")
                .antMatchers("/api/addGroup**").hasAnyRole("ADMIN", "CREATOR")
                .antMatchers("/api/addEvent**", "/api/updateEvent**").hasAnyRole("ADMIN", "CREATOR")
                .antMatchers("/api/getGroupById/**","/api/getGroups","/api/getGroupNames").hasAnyRole("USER", "ADMIN","CREATOR")
                .antMatchers("/api/eventByTitle/**","/api/eventById/**","/api/events").hasAnyRole("USER", "ADMIN","CREATOR")
                .antMatchers("/api/alertsCountNewEvent/**","/api/alertsNewEvent/**","/api/alertsCountEventIn15Min/**","/api/alertsEventIn15Min/**").hasAnyRole("USER", "ADMIN","CREATOR")
                .antMatchers("/api/getUser","/api/getEmails","/api/userByEmail/**","/api/curUser","/api/getGroupByUser/**").hasAnyRole("USER", "ADMIN","CREATOR")
                .antMatchers("/login").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");

        //Without Any Role Authorization
//                .antMatchers("/api/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .httpBasic();

    }


}
