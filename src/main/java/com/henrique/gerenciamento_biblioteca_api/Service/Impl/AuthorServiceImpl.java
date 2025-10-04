package com.henrique.gerenciamento_biblioteca_api.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henrique.gerenciamento_biblioteca_api.DTO.AuthorDTO;
import com.henrique.gerenciamento_biblioteca_api.DTO.Summary.BookSummaryDTO;
import com.henrique.gerenciamento_biblioteca_api.Model.AuthorModel;
import com.henrique.gerenciamento_biblioteca_api.Model.BookModel;
import com.henrique.gerenciamento_biblioteca_api.Repository.AuthorRepository;
import com.henrique.gerenciamento_biblioteca_api.Service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorModel createAuthor(AuthorDTO authorDTO) {
        AuthorModel authorModel = new AuthorModel();
        authorModel.setName(authorDTO.getName());
        return authorRepository.save(authorModel);
    }

    /*
     * O controller quer receber uma lista de todos os autores. FindAll busca por todos os autores na tabela, mas retorna em Model. O padrão é trabalhar com DTOs. Por isso é preciso converter todos os autores Model, em autores DTO. Isso é feito normalmente para o Id e Name. Entretanto ao chegar na lista de livros do autor ele recebe os livros em uma lista de livros Model, então eles também precisão ser passados para DTO. Para isso eu criei a versão Summary dos DTOs de Author e Book. As versões Summary não contém as listas de autor e book relacionadas para evitar loops
     */
    @Override
    public List<AuthorDTO> getAuthors() {
        List<AuthorModel> authorsList = authorRepository.findAll();

        return authorsList.stream()
                    .map(this::convertToAuthorDTO) // Mapeia cada AuthorModel para AuthorDTO
                    .collect(Collectors.toList());
    }

    // Converte AuthorModel para AuthorDTO
    private AuthorDTO convertToAuthorDTO(AuthorModel authorModel) {
        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setId(authorModel.getId());
        authorDTO.setName(authorModel.getName());

        // Converte a lista aninhada de BookModel para BookDTO
        if (authorModel.getBooks() != null) {
            List<BookSummaryDTO> bookSummaryDTO = authorModel.getBooks().stream()
                                                .map(this::convertToBookDTO)
                                                .collect(Collectors.toList());
            authorDTO.setBooks(bookSummaryDTO);
        }
        return authorDTO;
    }

    // Converte BookModel para BookSummaryDTO
    private BookSummaryDTO convertToBookDTO(BookModel bookModel) {
        BookSummaryDTO bookSummaryDTO = new BookSummaryDTO();
        bookSummaryDTO.setId(bookModel.getId());
        bookSummaryDTO.setTitle(bookModel.getTitle());
        bookSummaryDTO.setIsbn(bookModel.getIsbn());
        bookSummaryDTO.setPublication_year(bookModel.getPublication_year());
        bookSummaryDTO.setStatus(bookModel.getStatus());

        return bookSummaryDTO;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

}
