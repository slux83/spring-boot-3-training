package fr.slux.training.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails userDetailsAdmin = createUserDetails("admin", "12345", "ADMIN", "USERS");
        UserDetails userDetailsUser = createUserDetails("slux", "12345", "USERS");

        return new InMemoryUserDetailsManager(userDetailsAdmin, userDetailsUser);
    }

    private UserDetails createUserDetails(String username, String password, String... roles) {
        Function<String, String> passwordEncoder =
                input -> passwordEncoder().encode(input);
        return User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles(roles).build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
