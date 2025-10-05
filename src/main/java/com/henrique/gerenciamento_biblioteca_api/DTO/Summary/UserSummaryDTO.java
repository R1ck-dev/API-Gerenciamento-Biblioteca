package com.henrique.gerenciamento_biblioteca_api.DTO.Summary;

import com.henrique.gerenciamento_biblioteca_api.Enum.UserRoleEnum;

public class UserSummaryDTO {
    private Long id;
    private String name;
    private String email;
    private UserRoleEnum role;

    public UserSummaryDTO() {
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

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
