package com.henrique.gerenciamento_biblioteca_api.Service;

import java.util.List;

import com.henrique.gerenciamento_biblioteca_api.DTO.BookDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.CreateBookDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.BookModel;

public interface BookService {
    BookModel createBook(CreateBookDTO createBookDTO);

    List<BookDTO> getBooks();

    void deleteBook(Long id);
}
