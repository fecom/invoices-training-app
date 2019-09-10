package springLogSecure.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class ConfigurationUsersRole extends WebSecurityConfigurerAdapter {


    // referencia na datasource ???

    @Autowired
    private DataSource securityDataSource;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(securityDataSource);

//        User.UserBuilder user = User.withDefaultPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser(user.username("Riso").password("1111").roles("zamestnanec"))
//                .withUser(user.username("Palo").password("0000").roles("zamestnanec","manager"))
//                .withUser(user.username("Marek").password("8888").roles("zamestnanec","admin"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/managerisVymysleny/**").hasRole("MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN")
//
//
//                .anyRequest().authenticated() // toto dam iba ak neriesim role a je jedno kto sa prihlasi vidia vsetko
                .and()
                .formLogin()
                .loginPage("/loginForm")
//                              to authenticateUser si nejak osefuje spring na pozadi
                .loginProcessingUrl("/authenticateUser")
                .permitAll()



                .and()
                .logout().permitAll()
                .and()
//                              odkazuje na formular ktory vypise ze nemas pristup
                .exceptionHandling().accessDeniedPage("/zamietnutiePristupu");

    }
}
