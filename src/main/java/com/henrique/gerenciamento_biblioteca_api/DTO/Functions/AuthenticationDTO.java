package com.henrique.gerenciamento_biblioteca_api.DTO.Functions;

public class AuthenticationDTO {
    
    private String email;
    private String password;
    
    public AuthenticationDTO() {
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
