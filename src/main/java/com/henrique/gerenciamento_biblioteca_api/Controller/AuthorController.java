package com.henrique.gerenciamento_biblioteca_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.gerenciamento_biblioteca_api.DTO.AuthorDTO;
import com.henrique.gerenciamento_biblioteca_api.Service.AuthorService;

@RestController
public class AuthorController {
    
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    
    @PostMapping
    public ResponseEntity<String> createAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.createAuthor(authorDTO);
        return ResponseEntity.ok("Autor Criado com Sucesso!");
    }
}
