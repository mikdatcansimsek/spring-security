package yte.intern.springsecurity;

import org.hibernate.property.access.internal.PropertyAccessStrategyNoopImpl;
import org.hibernate.property.access.spi.PropertyAccessStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.disable())
                .logout(logOut-> logOut.disable())
                .csrf(csrf->csrf.disable())
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(CustomAuthenticationProvider customAuthenticationProvider){
        return new ProviderManager(customAuthenticationProvider);
    }
}
