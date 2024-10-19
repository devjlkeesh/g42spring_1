package dev.jlkeesh.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static final String[] WHITE_LIST = {
            "/auth/login",
            "/auth/register",
            "/css/**",
            "/js/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());

        http.authorizeRequests()
                .requestMatchers(WHITE_LIST).permitAll()
                .anyRequest().authenticated();

        http.formLogin(loginConfig ->
                loginConfig
                        .usernameParameter("uname")
                        .passwordParameter("pwd")
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/book", true)
        );

        http.rememberMe(rememberMeConfig ->
                rememberMeConfig.rememberMeParameter("rememberMe")
                        .rememberMeCookieName("abdullajon")
                        .tokenValiditySeconds(86400)
        );

        /*    http.httpBasic(Customizer.withDefaults());*/

        http.logout(logoutConfig ->
                logoutConfig.logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login")
        );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.withUsername("admin")
                .password("123")
                .roles("ADMIN")
                .build();
        UserDetails manager = User.withUsername("manager")
                .password("123")
                .roles("MANAGER")
                .build();
        UserDetails user = User
                .withUsername("user")
                .password("123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, manager, user);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        /*new BCryptPasswordEncoder();
        new SCryptPasswordEncoder();
        new DelegatingPasswordEncoder();*/
        return NoOpPasswordEncoder.getInstance();
    }

}
