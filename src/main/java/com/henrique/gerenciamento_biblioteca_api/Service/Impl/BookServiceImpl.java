package com.henrique.gerenciamento_biblioteca_api.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.CreateBookDTO;
import com.henrique.gerenciamento_biblioteca_api.Enum.BookStatusEnum;
import com.henrique.gerenciamento_biblioteca_api.Model.AuthorModel;
import com.henrique.gerenciamento_biblioteca_api.Model.BookModel;
import com.henrique.gerenciamento_biblioteca_api.Repository.AuthorRepository;
import com.henrique.gerenciamento_biblioteca_api.Repository.BookRepository;
import com.henrique.gerenciamento_biblioteca_api.Service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookModel createBook(CreateBookDTO createBookDTO) {
        List<AuthorModel> authors = authorRepository.findAllById(createBookDTO.getAuthorIds());

        BookModel bookModel = new BookModel();
        bookModel.setTitle(createBookDTO.getTitle());
        bookModel.setIsbn(createBookDTO.getIsbn());
        bookModel.setPublication_year(createBookDTO.getPublicationYear());
        bookModel.setStatus(BookStatusEnum.AVAILABLE);
        bookModel.setAuthors(authors);
        return bookRepository.save(bookModel);
    }
}
