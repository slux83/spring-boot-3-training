package fr.slux.training.springboot.springsecurity.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

// Uncomment this to use just basic auth security configuration
@Configuration
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class BasicAuthSecurityConfiguration {
    public enum Roles {
        USER_ROLE,
        ADMIN_ROLE
    }


    @Bean
    SecurityFilterChain securitySecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/todos").hasRole(Roles.USER_ROLE.name())
                .requestMatchers("/admin/**").hasRole(Roles.ADMIN_ROLE.name())
                .anyRequest().authenticated());
        // No session
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // No form login
        //http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);

        // Allow HTML frames
        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }

    /**
     * Browsers do not allow Ajax calls to resources outside the current domain.
     * For that we need Cross-Origin Resource Sharing (CORS) to be configured that allows you
     * to specify which cross-domain requests are allowed
     */
    /*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:8080");
            }
        };
    }
    */

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        // {noop}password = clear text password
        UserDetails user1 = User.withUsername("alessio")
                .password("{noop}alessio")
                .roles(Roles.USER_ROLE.name()).build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles(Roles.ADMIN_ROLE.name()).build();
        return new InMemoryUserDetailsManager(user1, admin);
    }
     */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource, BCryptPasswordEncoder passwordEncoder) {
        UserDetails user1 = User.withUsername("alessio")
                .password("alessio")
                .passwordEncoder(passwordEncoder::encode)
                .roles(Roles.USER_ROLE.name()).build();

        UserDetails admin = User.withUsername("admin")
                .password("admin")
                .passwordEncoder(passwordEncoder::encode)
                .roles(Roles.ADMIN_ROLE.name(), Roles.USER_ROLE.name()).build();

        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user1);
        jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DataSource dataSource() {
        // Default schema for users is already present, so we use that at startup
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcUserDetailsManager.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }
}
