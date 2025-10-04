package com.henrique.gerenciamento_biblioteca_api.DTO;

import java.util.List;

import com.henrique.gerenciamento_biblioteca_api.DTO.Summary.BookSummaryDTO;

public class AuthorDTO {

    private Long id;
    private String name;
    private List<BookSummaryDTO> books;

    public AuthorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BookSummaryDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookSummaryDTO> books) {
        this.books = books;
    }

    
}
