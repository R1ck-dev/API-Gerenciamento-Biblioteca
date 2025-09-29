package com.henrique.gerenciamento_biblioteca_api.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.BookDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.BookModel;

public interface BookService {
    BookModel createBook(BookDTO bookDTO);
}
