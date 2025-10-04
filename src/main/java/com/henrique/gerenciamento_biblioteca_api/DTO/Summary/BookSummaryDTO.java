package com.henrique.gerenciamento_biblioteca_api.DTO.Summary;

import com.henrique.gerenciamento_biblioteca_api.Enum.BookStatusEnum;

public class BookSummaryDTO {
    private Long id;
    private String title;
    private String isbn;
    private int publication_year;
    private BookStatusEnum status;

    public BookSummaryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public BookStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BookStatusEnum status) {
        this.status = status;
    }

}
