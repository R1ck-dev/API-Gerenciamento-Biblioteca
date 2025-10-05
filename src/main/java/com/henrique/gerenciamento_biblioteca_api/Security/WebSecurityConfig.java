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
                        .requestMatchers(HttpMethod.PUT, "/users/me").permitAll() // Atualiza Usuário
                        .requestMatchers(HttpMethod.GET, "/users").permitAll() // Lista Usuários
                        .requestMatchers(HttpMethod.POST, "/books").permitAll() // Cria Livro
                        .requestMatchers(HttpMethod.GET, "/books").permitAll() // Lista de Livros
                        .requestMatchers(HttpMethod.DELETE, "/books/{id}").permitAll() // Deleta livro por ID
                        .requestMatchers(HttpMethod.PUT, "/books/{id}").permitAll() // Atualiza Livro por ID
                        .requestMatchers(HttpMethod.POST, "/authors").permitAll() // Cria Autor
                        .requestMatchers(HttpMethod.GET, "/authors").permitAll() // Lista de Autores
                        .requestMatchers(HttpMethod.DELETE, "/authors/{id}").permitAll() // Deleta autor por ID
                        .requestMatchers(HttpMethod.PUT, "/authors/{id}").permitAll() // Atualiza Autor por ID
                        .requestMatchers(HttpMethod.POST, "/loans").permitAll() // Cria Empréstimos
                        .requestMatchers(HttpMethod.POST, "/loans/{id}/return").permitAll() // Devolve Empréstimos
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
