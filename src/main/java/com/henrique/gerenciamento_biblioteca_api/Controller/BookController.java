package com.henrique.gerenciamento_biblioteca_api.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.CreateBookDTO;
import com.henrique.gerenciamento_biblioteca_api.Service.BookService;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<String> createBook(@RequestBody CreateBookDTO createBookDTO) {
        bookService.createBook(createBookDTO);
        return ResponseEntity.ok("Livro Criado com Sucesso!");
    }
}
