package com.henrique.gerenciamento_biblioteca_api.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.BookDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.BookModel;
import com.henrique.gerenciamento_biblioteca_api.Repository.BookRepository;
import com.henrique.gerenciamento_biblioteca_api.Service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookModel createBook(BookDTO bookDTO) {
        BookModel bookModel = new BookModel();
        bookModel.setTitle(bookDTO.getTitle());
        bookModel.setIsbn(bookDTO.getIsbn());
        bookModel.setPublication_year(bookDTO.getPublication_year());
        bookModel.setStatus(bookDTO.getStatus());
        return bookRepository.save(bookModel);
    }
}
