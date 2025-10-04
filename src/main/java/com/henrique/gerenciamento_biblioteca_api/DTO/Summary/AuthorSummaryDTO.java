package com.henrique.gerenciamento_biblioteca_api.DTO.Summary;

public class AuthorSummaryDTO {
    private Long id;
    private String name;

    public AuthorSummaryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
