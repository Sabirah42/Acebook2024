package com.makersacademy.acebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/posts").hasRole("USER")
                .antMatchers("/my_profile").hasRole("USER")
                .antMatchers("/users").permitAll()
                .and()
                .formLogin()
                .loginPage("/user-login") //this is the default login page when visiting 8080...
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/posts") //when login is successful this is new default url
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout") //where to default to when logout function is run
                .logoutSuccessUrl("/user-login?logout") //when you click logout this is where you are directed
                .permitAll();
                //.csrf();


    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
