/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 9, 2022 - 10:26:17 PM
 *  ***************************************
 */

package com.nable.crib.corp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthWebSecurityConfiguration
{
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
         .csrf().disable()
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();

    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User
        .withUsername("nable1")
        .password("{noop}nable@123")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(user);
  }
}
