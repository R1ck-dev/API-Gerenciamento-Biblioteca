package com.henrique.gerenciamento_biblioteca_api.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.gerenciamento_biblioteca_api.DTO.BookDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.CreateBookDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.UpdateRequestBookDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.BookModel;
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

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Livro Deletado com Sucesso!");
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id, UpdateRequestBookDTO updateDTO) {
        
        BookModel updatedBook = bookService.updateBook(id, updateDTO);

        return ResponseEntity.ok(updatedBook);
    }
}
