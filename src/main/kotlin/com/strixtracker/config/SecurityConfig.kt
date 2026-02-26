package com.strixtracker.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                // Only authenticated users can access sightings
                auth.requestMatchers("/sightings/**").authenticated()
                // Everything else is public
                auth.anyRequest().permitAll()
            }
            .formLogin { form ->
                form.loginPage("/login")             // Custom login page
                    .defaultSuccessUrl("/sightings", true) // Redirect here after login
                    .permitAll()
            }
            .logout { logout ->
                logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/")       // Redirect to home after logout
                    .permitAll()
            }

        return http.build()
    }

    // Password encoder bean required for registration and authentication
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}