package com.henrique.gerenciamento_biblioteca_api.DTO.Functions;

public class CreateLoanDTO {
    private Long bookId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
