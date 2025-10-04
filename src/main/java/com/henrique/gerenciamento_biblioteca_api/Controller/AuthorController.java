package com.henrique.gerenciamento_biblioteca_api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.gerenciamento_biblioteca_api.DTO.AuthorDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.UpdateRequestAuthorDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.AuthorModel;
import com.henrique.gerenciamento_biblioteca_api.Service.AuthorService;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/authors")
    public ResponseEntity<String> createAuthor(@RequestBody AuthorDTO authorDTO) {
        authorService.createAuthor(authorDTO);
        return ResponseEntity.ok("Autor Criado com Sucesso!");
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        return ResponseEntity.ok(authorService.getAuthors());
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Autor Deletado com Sucesso!");
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorModel> updateAuthor(@PathVariable Long id,
            @RequestBody UpdateRequestAuthorDTO updateDTO) {

        AuthorModel updatedAuthor = authorService.updateAuthor(id, updateDTO);

        return ResponseEntity.ok(updatedAuthor);
    }

}
