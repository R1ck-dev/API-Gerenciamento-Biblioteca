package com.henrique.gerenciamento_biblioteca_api.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final SecurityFilter securityFilter;

    public WebSecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // Registra Usuário
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Login de Usuário
                        .requestMatchers(HttpMethod.PUT, "/users/me").hasAnyRole("ADMIN", "USER") // Atualiza Usuário
                        .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN") // Lista Usuários
                        .requestMatchers(HttpMethod.DELETE, "/users").hasRole("ADMIN") // Deleta Usuários
                        .requestMatchers(HttpMethod.POST, "/books").hasRole("ADMIN") // Cria Livro
                        .requestMatchers(HttpMethod.GET, "/books").hasAnyRole("ADMIN", "USER") // Lista de Livros
                        .requestMatchers(HttpMethod.DELETE, "/books/{id}").hasRole("ADMIN") // Deleta livro por ID
                        .requestMatchers(HttpMethod.PUT, "/books/{id}").hasRole("ADMIN") // Atualiza Livro por ID
                        .requestMatchers(HttpMethod.POST, "/authors").hasRole("ADMIN") // Cria Autor
                        .requestMatchers(HttpMethod.GET, "/authors").hasAnyRole("ADMIN", "USER") // Lista de Autores
                        .requestMatchers(HttpMethod.DELETE, "/authors/{id}").hasRole("ADMIN") // Deleta autor por ID
                        .requestMatchers(HttpMethod.PUT, "/authors/{id}").hasRole("ADMIN") // Atualiza Autor por ID
                        .requestMatchers(HttpMethod.POST, "/loans").hasAnyRole("ADMIN", "USER") // Cria Empréstimos
                        .requestMatchers(HttpMethod.POST, "/loans/{id}/return").hasAnyRole("ADMIN", "USER") // Devolve Empréstimos
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
