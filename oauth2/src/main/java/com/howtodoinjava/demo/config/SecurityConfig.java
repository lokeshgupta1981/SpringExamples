package com.howtodoinjava.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*http.requestMatchers()
          .antMatchers("/login", "/oauth/authorize")
          .and()
          .authorizeRequests()
          .anyRequest().authenticated()
          .and()
          .formLogin().permitAll();*/

      http
          .authorizeHttpRequests((authorizeHttpRequests) ->
              authorizeHttpRequests
                  .requestMatchers("/login", "/oauth/authorize")
                  .authenticated()
          )
          .formLogin(Customizer.withDefaults());

      return http.build();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.inMemoryAuthentication()
            .withUser("humptydumpty")
            .password(passwordEncoder().encode("123456"))
            .roles("USER");
    }*/

  @Bean
  public UserDetailsService userDetailsService() {

    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER").build());
    return manager;
  }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
