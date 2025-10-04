package com.henrique.gerenciamento_biblioteca_api.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.BookDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.CreateBookDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Functions.UpdateRequestBookDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Summary.AuthorSummaryDTO;
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
        // Retorna todos os autores do ID indicado no CreateBookDTO
        List<AuthorModel> authors = authorRepository.findAllById(createBookDTO.getAuthorIds());

        BookModel bookModel = new BookModel();
        bookModel.setTitle(createBookDTO.getTitle());
        bookModel.setIsbn(createBookDTO.getIsbn());
        bookModel.setPublication_year(createBookDTO.getPublicationYear());
        bookModel.setStatus(BookStatusEnum.AVAILABLE);
        // Seta os autores. Quando o Objeto final for criado e salvo, já será adicionado
        // na tabela author_model devido as anotações do JPA no BookModel
        bookModel.setAuthors(authors);

        return bookRepository.save(bookModel);
    }

    @Override
    public List<BookDTO> getBooks() {
        List<BookModel> booksList = bookRepository.findAll();

        return booksList.stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    private BookDTO convertToBookDTO(BookModel bookModel) {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(bookModel.getId());
        bookDTO.setTitle(bookModel.getTitle());
        bookDTO.setIsbn(bookModel.getIsbn());
        bookDTO.setPublication_year(bookModel.getPublication_year());
        bookDTO.setStatus(bookModel.getStatus());

        if (bookModel.getAuthors() != null) {
            List<AuthorSummaryDTO> authorSummaryDTO = bookModel.getAuthors().stream()
                    .map(this::convertToAuthorDTO)
                    .collect(Collectors.toList());
            bookDTO.setAuthors(authorSummaryDTO);
        }
        return bookDTO;
    }

    private AuthorSummaryDTO convertToAuthorDTO(AuthorModel authorModel) {
        AuthorSummaryDTO authorSummaryDTO = new AuthorSummaryDTO();

        authorSummaryDTO.setId(authorModel.getId());
        authorSummaryDTO.setName(authorModel.getName());

        return authorSummaryDTO;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookModel updateBook(Long id, UpdateRequestBookDTO updateDTO) {
        BookModel book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        if (updateDTO.getTitle() != null && !updateDTO.getTitle().isEmpty()) {
            book.setTitle(updateDTO.getTitle());
        }

        if (updateDTO.getIsbn() != null && !updateDTO.getIsbn().isEmpty()) {
            book.setIsbn(updateDTO.getIsbn());
        }

        if (updateDTO.getPublication_year() != null) {
            book.setPublication_year(updateDTO.getPublication_year());
        }

        return bookRepository.save(book);
    }

}
