package com.henrique.gerenciamento_biblioteca_api.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// Repositório e Serviço
import com.henrique.gerenciamento_biblioteca_api.Repository.UserRepository;
import com.henrique.gerenciamento_biblioteca_api.Service.TokenService; // Importante: Nossa interface customizada

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Autowired
    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        var token = this.recoverToken(request);
        
        if (token != null) {
            // Valida o token e extrai o email (subject)
            var email = tokenService.validateToken(token);

            // Se o token for válido e tiver um email
            if (email != null && !email.isEmpty()) {
                // Carrega os detalhes do usuário a partir do banco de dados
                UserDetails user = userRepository.findByEmail(email);

                // Se o usuário existir, cria um objeto de autenticação
                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    
                    // Define o usuário como autenticado no contexto de segurança do Spring para esta requisição
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        
        // Continua a cadeia de filtros, passando a requisição para o próximo
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        // Remove o prefixo "Bearer " para obter apenas a string do token
        return authHeader.substring(7);
    }
}