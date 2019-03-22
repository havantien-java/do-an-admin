package shop.create.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.create.service.impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register", "/index","/").permitAll()
                .antMatchers("/menber/**").hasRole("MEMBER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").successHandler(customSuccessHandler)
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .and()
                .rememberMe()
                .key("rem-me-key")
                .rememberMeParameter("remember")
                .rememberMeCookieName("rememberlogin")
                .tokenValiditySeconds(100)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .logout().logoutSuccessUrl("/login").logoutUrl("/logout").permitAll();
    }

}
