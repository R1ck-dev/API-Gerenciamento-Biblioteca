package com.henrique.gerenciamento_biblioteca_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.gerenciamento_biblioteca_api.DTO.UserDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.AuthenticationDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.LoginResponseDTO;
// Model e Services
import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;
import com.henrique.gerenciamento_biblioteca_api.Security.TokenService;
import com.henrique.gerenciamento_biblioteca_api.Service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok("Usuário Criado com Sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        // Cria o objeto de autenticação com as credenciais do DTO
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

        // Autentica o usuário. Se as credenciais forem inválidas, o Spring Security lança uma exceção aqui.
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // Se a autenticação for bem-sucedida, gera o token JWT
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        // Retorna o token dentro de um DTO de resposta com status 200 OK
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}