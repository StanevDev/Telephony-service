package edu.jam.telephony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public AppSecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Throwable{
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT phone_number, password FROM subscriber"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login")
                    .usernameParameter("phone_number")
                    .passwordParameter("password")
                    .permitAll()
                .and().csrf();
    }
}
