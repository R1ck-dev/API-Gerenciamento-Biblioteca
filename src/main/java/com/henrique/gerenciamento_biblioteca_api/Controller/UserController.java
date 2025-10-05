package com.henrique.gerenciamento_biblioteca_api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.gerenciamento_biblioteca_api.DTO.UserDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.AuthenticationDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.LoginResponseDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.UpdateRequestUserDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Summary.UserSummaryDTO;
// Model e Services
import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;
import com.henrique.gerenciamento_biblioteca_api.Security.TokenService;
import com.henrique.gerenciamento_biblioteca_api.Service.UserService;

@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager,
            TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok("Usuário Criado com Sucesso!");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        // Cria o objeto de autenticação com as credenciais do DTO
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());

        // Autentica o usuário. Se as credenciais forem inválidas, o Spring Security
        // lança uma exceção aqui.
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // Se a autenticação for bem-sucedida, gera o token JWT
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        // Retorna o token dentro de um DTO de resposta com status 200 OK
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PutMapping("/users/me")
    public ResponseEntity<UserDTO> updateUser(Authentication authentication,
            @RequestBody UpdateRequestUserDTO updateDTO) {

        UserModel currentUser = (UserModel) authentication.getPrincipal();

        UserDTO updatedUser = userService.updateUser(currentUser.getId(), updateDTO);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserSummaryDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

}