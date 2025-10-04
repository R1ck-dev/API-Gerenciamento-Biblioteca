package com.henrique.gerenciamento_biblioteca_api.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.CreateBookDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.BookModel;

public interface BookService {
    BookModel createBook(CreateBookDTO createBookDTO);
}
