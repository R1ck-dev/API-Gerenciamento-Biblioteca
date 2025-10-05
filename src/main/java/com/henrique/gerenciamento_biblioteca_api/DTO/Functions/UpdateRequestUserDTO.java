package com.henrique.gerenciamento_biblioteca_api.DTO.Functions;

public class UpdateRequestUserDTO {
    private String name;
    private String email;

    public UpdateRequestUserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
