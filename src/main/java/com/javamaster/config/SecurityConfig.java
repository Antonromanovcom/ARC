package com.javamaster.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("bob").password("1234").roles("USER");
    }

    //.csrf() is optional, enabled by default, if using WebSecurityConfigurerAdapter constructor
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        /* <intercept-url pattern="/login/**" access="IS_AUTHENTICATED_ANONYMOUSLY"/>
		<intercept-url pattern="/resources/**" access="IS_AUTHENTICATED_ANONYMOUSLY"/>
		<intercept-url pattern="/**" access="ROLE_USER" />
		*/

        http.authorizeRequests()
                .antMatchers("/login/**").access("hasRole('IS_AUTHENTICATED_ANONYMOUSLY')")
                .antMatchers("/resources/**").access("hasRole('ROLE_USER')")
                .antMatchers("/**").access("hasRole('ROLE_USER')")
                .and().formLogin().defaultSuccessUrl("/", false);
    }


}
