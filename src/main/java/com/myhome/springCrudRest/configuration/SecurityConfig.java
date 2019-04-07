package com.myhome.springCrudRest.configuration;

/**
 * @author Nick Dolgopolov (nick_kerch@mail.ru; https://github.com/Absent83/)
 */



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService userDetailsService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/"); //не использовать цепочки фильтров (отключается Security) для указанных url (для общих ресурсов)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable()

                //без сессий (для REST)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()

                //порядок матчеров имее значение: сработает тот, который первый.
                //добавляет фильтр ананонимной аутентификации.
//                .authorizeRequests().antMatchers("/public").anonymous()
//                .and()

//                .authorizeRequests().antMatchers("/authorized").authenticated()
//                .and()

                .authorizeRequests()
                    .antMatchers("/users/**", "/users", "/api/users/**", "/api/users") //todo разница /users и /users/
                    .hasAuthority("ADMIN")
                    .and()

                .authorizeRequests()
                    .antMatchers("/profile")
                    .permitAll()
                    //.authenticated()
                    .and()

//                .authorizeRequests()
//                    .antMatchers("/profile")
//                    .anonymous()
//                    .and()

                .anonymous()
                    .authorities("ROLE_ANONYMOUS")
                    .principal("anonim")
                    .and()

                //запрашивает login-password из head (открывает окно аутентификации)
                //.httpBasic();

                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/profile")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()

//                .rememberMe()
//                .key("myAppKey")
//                .tokenValiditySeconds(60)
//                  .and()

                .logout()
                    .logoutUrl("/logout");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("=== SecurityConfig === === configureGlobal ===");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

//        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("1").password("1").roles("ADMIN");
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
        return encoder;
    }


//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }


//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        PasswordEncoder encoder = new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return rawPassword.toString() == encodedPassword;
//            }
//        };
//        return encoder;
//    }

}

